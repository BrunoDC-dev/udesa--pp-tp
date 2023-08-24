--Tunel.hs

module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where
import Link
import City
import Point
import Quality

import Data.List
import GHC.Exts.Heap (GenClosure(link))
data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool
connectsT city1 city2 (Tun links)
 
  | distanceC city1 city2 == 0 = error "No es posible crear un túnel con la misma ciudad."
  | length links < 1 = error "No es posible crear un túnel con menos de un link."
  | length links ==1  =  linksL city1 city2 (head links)
 
  | connectsL city1 (head links) && connectsL city2 (last links) 
    && not( isCityInSecondLink city1 links) && not (isCityInPenultimateLink city2 links) = True
 
  | connectsL city2 (head links) && connectsL city1 (last links) 
    && not (isCityInSecondLink city2 links) &&  not (isCityInPenultimateLink city1 links) = True
 
  | otherwise = False
 
  where
 
    isCityInSecondLink :: City -> [Link] -> Bool
    isCityInSecondLink city links
      | length links >= 2 =  connectsL city (links !! 1)
      | otherwise = False

    isCityInPenultimateLink :: City ->[Link] -> Bool
    isCityInPenultimateLink city links
      | length links >= 2 = connectsL city  (last (init links))
      |  otherwise = False



usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links


delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links) -- sumo todas las demoras de los links que componen el tunel