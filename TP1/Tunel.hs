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
  | distanceC city1 city2 == 0 = error "No es posible crear un túnel con la misma ciudad."
  | length links < 1 = error "No es posible crear un túnel con menos de un link."
  | connectsL city1 (head links) && connectsL city2 (last links) && notInSecondLink && notInSecondToLastLink = True
  | connectsL city2 (head links) && connectsL city1 (last links) && notInSecondLink && notInSecondToLastLink = True
  | otherwise = False
  where
    notInSecondLink = not (city2 `connectedWith` secondLink)
    notInSecondToLastLink = not (city1 `connectedWith` secondToLastLink)

    secondLink = if length links >= 2 then links !! 1 else error "No hay suficientes links en el túnel."
    secondToLastLink = if length links >= 2 then last (init links) else error "No hay suficientes links en el túnel."
    
    connectedWith :: City -> Link -> Bool
    connectedWith city link = city `connectsL` link


usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links 

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links) -- sumo todas las demoras de los links que componen el tunel