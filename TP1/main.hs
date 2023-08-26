import  City
import  Link
import  Tunel
import  Point
import Quality
import  Region
import Control.Exception
import System.IO.Unsafe
import Control.Applicative (Alternative(empty))
import GHC.Exts.Heap (GenClosure(link))




testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

---TestPoints

testPont =  newP 2 2

----TestCities
testCity0 = newC "testCity5" (newP 0 0)
testCity = newC "testCity" (newP 2 2)
testCity2 = newC "testCity2" (newP 3 3)
testCity3 = newC "testCity3" (newP 4 4)
testCity4 = newC "testCity4" (newP 5 5)
testCity5 = newC "testCity5" (newP 6 6)
testCity6 = newC "testCity6" (newP 3 4)
testCity7 = newC "testCity7" (newP 6 8)

-----TestQualities
testQuality1 = newQ "A" 1 0.5
testQuality2 = newQ "B" 2 0.2


----TestLinks
testlink= newL testCity2 testCity testQuality1
testlink2= newL testCity testCity4 testQuality1
testlink3= newL testCity2 testCity3 testQuality1
testlink4= newL testCity4 testCity5 testQuality2
testlink6= newL testCity0 testCity6 testQuality1
testlink7= newL testCity6 testCity7 testQuality1

--- testTunels

testTunel = newT [testlink, testlink2]

----testRegions
emptyTestRegion = newR 
regionWithCity = foundR emptyTestRegion testCity
regionWithCities =foundR (foundR regionWithCity testCity2) testCity4
regionWithLink =  linkR regionWithCities testCity testCity2 testQuality1
regionForTunel =  linkR regionWithLink testCity4 testCity testQuality1

finalregion = tunelR regionForTunel [testCity2 ,testCity , testCity4]

exceptionsTest= [ 
    testF (newL testCity testCity testQuality1),
    testF (linksL testCity testCity testlink), 
    testF (connectsT testCity testCity testTunel),
    testF (connectsT testCity testCity2 (newT [])),
    testF(foundR regionWithCity (newC "testCity" (newP 2 2))),
    testF(linkR regionWithCities testCity5 testCity2 testQuality1),
    testF(linkR regionWithLink testCity2 testCity testQuality2),
    testF(tunelR regionForTunel []),
    testF(tunelR regionForTunel [testCity2]),
    testF(tunelR regionForTunel [testCity2,testCity2]),
    testF(tunelR regionForTunel [testCity5,testCity2,testCity4]),
    testF(tunelR regionForTunel [testCity4,testCity2,testCity]),
    testF(tunelR finalregion [testCity2, testCity ,testCity4]),
    testF(tunelR finalregion [ testCity ,testCity4]),
    testF(delayR finalregion testCity testCity4),
    testF(availableCapacityForR finalregion testCity5 testCity4),
    testF(availableCapacityForR finalregion testCity2 testCity2),
    testF(availableCapacityForR finalregion testCity4 testCity2)
                ]
tests = [
         newP 2 2 == testPont, 
         difP (newP 0 0) (newP 3 4) == 5.0,

         newC "testCity" (newP 2 2) ==testCity, 
         nameC (newC "testCity" (newP 2 2)) == "testCity",
         distanceC (newC "testCity" (newP 0 0)) (newC "testCity2" (newP 3 4)) == 5.0,

         newQ "A" 1 0.5 == testQuality1,  
         capacityQ (newQ "A" 1 0.1) == 1,
         delayQ (newQ "A" 1 0.1) == 0.1,

         newL testCity2 testCity testQuality1 == testlink, 
         connectsL testCity (newL testCity2 testCity testQuality1),
         not (connectsL testCity (newL testCity2 testCity3 testQuality1)),
         linksL testCity testCity2 (newL testCity2 testCity testQuality1),
         not (linksL testCity testCity2 (newL testCity2 testCity3 testQuality1)),
         capacityL (newL testCity2 testCity (newQ "A" 2  0.2)) == 2,
         delayL (newL testCity0 testCity6 testQuality1) == distanceC testCity0 testCity6 * delayQ testQuality1,

         newT [testlink, testlink2] == testTunel, 
         connectsT testCity4 testCity2 (newT [testlink,testlink2]),
         not (connectsT testCity testCity2 (newT [testlink,testlink2])),
         connectsT testCity4 testCity2 (newT [testlink, testlink2]),
         connectsT testCity5 testCity (newT [testlink, testlink3, testlink4]),
         not (connectsT testCity5 testCity (newT [testlink, testlink2, testlink3])),
         usesT testlink (newT [testlink, testlink2]),
         delayT (newT [testlink6, testlink7]) == delayL testlink6 + delayL testlink7,

         newR  == emptyTestRegion,
         foundR emptyTestRegion testCity == regionWithCity, 
         linkR regionWithCities testCity testCity2 testQuality1 ==regionWithLink,
         tunelR regionForTunel [testCity2 ,testCity , testCity4] == finalregion,
         connectedR finalregion testCity2 testCity4,
         not (connectedR finalregion testCity testCity4),
         linkedR finalregion testCity testCity2,
         not (linkedR finalregion testCity2 testCity4),
         delayR finalregion testCity2 testCity4 == delayT testTunel,
         availableCapacityForR finalregion testCity2 testCity == 0
         ] ++ exceptionsTest

state tests | and tests = "Correcto!"
            | otherwise = "Hubo errores :("

main = print tests