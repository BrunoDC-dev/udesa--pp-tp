import  City
import  Link
import  Tunel
import  Point
import Quality
import  Region
import Control.Exception
import System.IO.Unsafe

fallo :: IO a -> IO Bool
fallo action = do
    result <- tryJust isException action
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

testF :: IO Bool -> Bool
testF action = unsafePerformIO action

t = [ testF (fallo (print ( tunelR testForTunnel [] )))]

testCity = newC "testCity" (newP 2 2)
testCity2 = newC "testCity2" (newP 3 3)
testCity3 = newC "testCity3" (newP 4 4)
testCity4 = newC "testCity4" (newP 5 5)
testCity5 = newC "testCity5" (newP 6 6)

testQuality1 = newQ "A" 2 1
testQuality2 = newQ "B" 2 0.2

testlink= newL testCity2 testCity testQuality1
testlink2= newL testCity testCity4 testQuality2
testlink3= newL testCity2 testCity3 testQuality1
testlink4= newL testCity4 testCity5 testQuality2
testlink5= newL testCity testCity2 testQuality1
testTunel = newT [testlink5, testlink2,testlink4 ]
testTunel2 = newT [testlink3,testlink4]
test = newT []
arrayCity = [testCity,testCity2,testCity4]
arrayLink = [testlink3,testlink,testlink4,testlink2]
arrayTunel = [testTunel,testTunel2] 

addArrayCity :: Region -> [City] -> Region
addArrayCity region [] = region
addArrayCity region (c:cs) =  addArrayCity (foundR region c) cs

adLink :: Region -> [Link] -> Region
adLink region [] = region
--adLink region (l:ls) = adLink (foundL region l) ls

adTunel :: Region -> [Tunel] -> Region
adTunel region [] = region
--adTunel region (t:ts) = adTunel (foundT region t) ts

emptyTestRegion = newR 
testCitiesRegion = addArrayCity emptyTestRegion arrayCity
testLinksRegion = adLink emptyTestRegion arrayLink
testFullRegion = adTunel (adLink (addArrayCity emptyTestRegion arrayCity) arrayLink) arrayTunel

testForTunnel= Reg [testCity, testCity2, testCity4, testCity5] [testlink, testlink2] []


tests = [---newP 2 2 == Poi 2 2, 
         difP (newP 0 0) (newP 3 4) == 5.0,

         --newC "testCity" (newP 2 2) == Cit "testCity" (newP 2 2), 
         nameC (newC "testCity" (newP 2 2)) == "testCity",
         distanceC (newC "testCity" (newP 0 0)) (newC "testCity2" (newP 3 4)) == 5.0,

         --newQ "A" 1 0.1 == Qua "A" 1 0.1, 
         capacityQ (newQ "A" 1 0.1) == 1,
         delayQ (newQ "A" 1 0.1) == 0.1,

         --newL testCity2 testCity testQuality1 == Lin testCity2 testCity testQuality1, 
         connectsL testCity (newL testCity2 testCity testQuality1),
         not (connectsL testCity (newL testCity2 testCity3 testQuality1)),
         linksL testCity testCity2 (newL testCity2 testCity testQuality1),
         not (linksL testCity testCity2 (newL testCity2 testCity3 testQuality1)),
         capacityL (newL testCity2 testCity testQuality1) == 1,
         delayL (newL testCity2 testCity testQuality1) == 0.1,

         ---newT [testlink, testlink2] == Tun [testlink, testlink2], 
         connectsT testCity testCity2 (newT [testlink]),
         not (connectsT testCity4 testCity2 (newT [testlink])),
         connectsT testCity4 testCity2 (newT [testlink, testlink2]),
         connectsT testCity5 testCity (newT [testlink, testlink3, testlink4]),
         not (connectsT testCity5 testCity (newT [testlink, testlink2, testlink3])),
         usesT testlink (newT [testlink, testlink2]),
         delayT (newT [testlink, testlink2]) == 0.3,

         newR == Reg [] [] [],

         True]

state tests | and tests = "Correcto!"
            | otherwise = "Hubo errores :("

main = print tests