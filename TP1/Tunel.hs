--Tunel.hs
module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where
import Link
import City
import Point

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool
connectsT city1 city2 (Tun []) = False
connectsT city1 city2 (Tun (link:rest)) =
  linksL city1 city2 link || connectsT city1 city2 (Tun rest)

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun [] ) = False
usesT link (Tun (link2:rest)) = link == link2 || usesT link (Tun rest)  


delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun [] ) = 0
delayT (Tun (link:rest)) = delayL link + delayT (Tun rest)
