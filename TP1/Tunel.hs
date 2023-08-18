--Tunel.hs
module Tunel ( Tunel(Tun), newT, connectsT, usesT, delayT )
   where
import Link
import City
import Point

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool
connectsT city1 city2 (Tun links)= any (\link -> linksL city1 city2 link) links -- indica si estas dos ciudades distintas estan conectadas mediante este tunel

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links  


delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links) -- sumo todas las demoras de los links que componen el tunel