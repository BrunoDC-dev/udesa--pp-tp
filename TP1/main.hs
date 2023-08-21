import  City
import  Link
import  Tunel
import  Point
import Quality
import  Region

testCity = newC "testCity" (newP 2 2)
testCity2 = newC "testCity2" (newP 3 3)
testCity3 = newC "testCity3" (newP 4 4)
testCity4 = newC "testCity4" (newP 5 5)
testCity5 = newC "testCity5" (newP 6 6)

testQuality1 = newQ "A" 1 0.1
testQuality2 = newQ "B" 20 0.2

testlink= newL testCity2 testCity testQuality1
testlink2= newL testCity testCity4 testQuality2
testlink3= newL testCity2 testCity3 testQuality1
testlink4= newL testCity3 testCity5 testQuality2

testTunel = newT [testlink,testlink2, testlink4,testlink3]
testTunel2 = newT [testlink3,testlink4]

arrayCity = [testCity,testCity2,testCity4]
arrayLink = [testlink3,testlink,testlink4,testlink2]
arrayTunel = [testTunel,testTunel2] 

addArrayCity :: Region -> [City] -> Region
addArrayCity region [] = region
addArrayCity region (c:cs) =  addArrayCity (foundR region c) cs

adLink :: Region -> [Link] -> Region
adLink region [] = region
adLink region (l:ls) = adLink (foundL region l) ls

adTunel :: Region -> [Tunel] -> Region
adTunel region [] = region
adTunel region (t:ts) = adTunel (foundT region t) ts

emptyTestRegion = newR 
testCitiesRegion = addArrayCity emptyTestRegion arrayCity
testLinksRegion = adLink emptyTestRegion arrayLink
testFullRegion = adTunel (adLink (addArrayCity emptyTestRegion arrayCity) arrayLink) arrayTunel

tests = [newP 2 2 == Poi 2 2, 
         difP (newP 0 0) (newP 3 4) == 5.0,

         newC "testCity" (newP 2 2) == Cit "testCity" (newP 2 2), 
         nameC (newC "testCity" (newP 2 2)) == "testCity",
         distanceC (newC "testCity" (newP 0 0)) (newC "testCity2" (newP 3 4)) == 5.0,

         newQ "A" 1 0.1 == Qua "A" 1 0.1, 
         capacityQ (newQ "A" 1 0.1) == 1,
         delayQ (newQ "A" 1 0.1) == 0.1,

         newL testCity2 testCity testQuality1 == Lin testCity2 testCity testQuality1, 
         connectsL testCity (newL testCity2 testCity testQuality1) == True,
         connectsL testCity (newL testCity2 testCity3 testQuality1) == False,
         linksL testCity testCity2 (newL testCity2 testCity testQuality1) == True,
         linksL testCity testCity2 (newL testCity2 testCity3 testQuality1) == False,
         capacityL (newL testCity2 testCity testQuality1) == 1,
         delayL (newL testCity2 testCity testQuality1) == 0.1,

         newT [testlink, testlink2] == Tun [testlink, testlink2], 
         connectsT testCity testCity2 (newT [testlink]) == True,
         connectsT testCity4 testCity2 (newT [testlink]) == False,
         connectsT testCity4 testCity2 (newT [testlink, testlink2]) == True,
         connectsT testCity5 testCity (newT [testlink, testlink3, testlink4]) == True,
         connectsT testCity5 testCity (newT [testlink, testlink2, testlink3]) == False,
         usesT testlink (newT [testlink, testlink2]) == True,
         delayT (newT [testlink, testlink2]) == 0.3,

         newR == Reg [] [] [],
         foundR emptyTestRegion testCity == Reg [testCity] [] [],
         foundT emptyTestRegion testTunel == Reg [] [] [testTunel],
         foundL emptyTestRegion testlink == Reg [] [testlink] [],
         sameRegion (Reg [testCity, testCity2, testCity3] [] []) testCity testCity2 == True,
         sameRegion (Reg [testCity, testCity3] [] []) testCity testCity2 == False,
         minimumCapacity [testlink, testlink2, testlink3, testlink4] == 1,
         linkExists testCity testCity2 [testlink2, testlink3, testlink, testlink4] == True,

         True]

state tests | and tests = "Correcto!"
            | otherwise = "Hubo errores :("

main = print tests