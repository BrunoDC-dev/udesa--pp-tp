--Tunel.hs

module Tunel ( Tunel(Tun), newT, connectsT, usesT, delayT)
   where
import Link
import City
import Point
import Quality 

import Data.List
data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool
connectsT city1 city2 (Tun links) 
  |distanceC city1 city2 == 0 = error "no es posible crear un tunel con la misma ciudad"
  |length links < 1 = error "`no es posible crear un tunel con menos de un link"
  |connectsL city1 (head links) && connectsL city2 (last links) = True
  |connectsL city2  (head links) && connectsL city1 (last links) =True
  |otherwise = error "Las ciudades no son los extremos del tunel"

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links 

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links) -- sumo todas las demoras de los links que componen el tunel