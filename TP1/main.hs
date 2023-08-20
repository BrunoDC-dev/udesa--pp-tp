import  City
import  Link
import  Tunel
--import  Region
import  Point
import Quality
import  Region

testCity = newC "testCity" (newP 2 2)
testCity2 = newC "testCity2" (newP 3 3)
testCity3 = newC "testCity3" (newP 4 4)
testCity4 = newC "testCity4" (newP 5 5)
testCity5 = newC "testCity5" (newP 6 6)

testQuality1 = newQ "A" 10 0.1
testQuality2 = newQ "B" 20 0.2

testlink= newL testCity2 testCity testQuality1
testlink2= newL testCity testCity4 testQuality2
testlink3= newL testCity2 testCity3 testQuality1
testlink4= newL testCity3 testCity5 testQuality2

testTunel = newT [testlink,testlink2, testlink4,testlink3]
testTunel2 = newT [testlink3,testlink4]

testRegion = newR 

arrayCity = [testCity,testCity2,testCity4]
arrayLink = [testlink3,testlink,testlink4,testlink2]
arrayTunel = [testTunel,testTunel2] 

addArrayCity :: Region -> [City] -> Region
addArrayCity region [] = region
addArrayCity region (x:xs) =  addArrayCity (foundR region x) xs

adLink :: Region -> [Link] -> Region
adLink region [] = region
adLink region (x:xs) = adLink (foundL region x) xs

adTunel :: Region -> [Tunel] -> Region
adTunel region [] = region
adTunel region (x:xs) = adTunel (foundT region x) xs


