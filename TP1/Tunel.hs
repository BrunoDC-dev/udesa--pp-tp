--Tunel.hs

module Tunel ( Tunel(Tun), newT, connectsT, usesT, delayT,repeatCity, getNonRepeatedCities,checkCitiesInNonRepeated )
   where
import Link
import City
import Point
import Quality (Quality ( Qua))

import Data.List
data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

----Funciones Propiasss--------
repeatCity :: [Link] -> City -> Bool
repeatCity linkList city = length (filter (\(Lin c1 c2 _) -> c1 == city || c2 == city) linkList) > 1

getNonRepeatedCities :: [Link] -> [City]
getNonRepeatedCities linkList = filter (\city -> not (repeatCity linkList city)) allCities
  where
    allCities = nub $ concatMap (\(Lin c1 c2 _) -> [c1, c2]) linkList


checkCitiesInNonRepeated :: [Link] -> City -> City -> Bool
checkCitiesInNonRepeated linkList city1 city2 =
  let nonRepeatedCities = getNonRepeatedCities linkList
  in city1 `elem` nonRepeatedCities && city2 `elem` nonRepeatedCities
----------------------------------
connectsT :: City -> City -> Tunel -> Bool
connectsT city1 city2 (Tun links) = checkCitiesInNonRepeated links city1 city2

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links  


delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links) -- sumo todas las demoras de los links que componen el tunel