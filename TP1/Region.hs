--Region.hs


module Region ( Region, newR, foundR, linkR, tunelR, connectedR,linkedR, delayR, availableCapacityForR,foundL, foundT)
   where
import City 
import Link 
import Tunel
import Quality

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)
newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city = Reg (city:cities) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) city1 city2 quality =
  let newLink = newL city1 city2 quality
  in Reg cities (newLink : links) tunnels

tunelR :: Region -> [City] -> Region
tunelR (Reg cities links tunnels) tunnelCities =
  let linksInTunnel = findLinksInTunnel tunnelCities links
      newTunnel = newT linksInTunnel
  in Reg cities links (newTunnel : tunnels)

findLinksInTunnel :: [City] -> [Link] -> [Link]
findLinksInTunnel _ [] = []
findLinksInTunnel tunnelCities (link@(Lin city1 city2 _):rest)
  | all (`elem` [city1, city2]) tunnelCities = link : findLinksInTunnel tunnelCities rest
  | otherwise = findLinksInTunnel tunnelCities rest

connectedR :: Region -> City -> City -> Bool
connectedR (Reg _ _ tunnels) city1 city2 = any (\tunnel -> connectsT city1 city2 tunnel) tunnels

linkedR :: Region -> City -> City -> Bool
linkedR (Reg _ links _) city1 city2 = any (\link -> linksL city1 city2 link) links

delayR :: Region -> City -> City -> Float
delayR region@(Reg _ _ tunnels) city1 city2
  | connectedR region city1 city2 = delayT (findTunnel city1 city2 tunnels)
  | otherwise = 0.0

findTunnel :: City -> City -> [Tunel] -> Tunel
findTunnel city1 city2 (tunnel@(Tun links):rest)
  | connectsT city1 city2 tunnel = tunnel
  | otherwise = findTunnel city1 city2 rest

availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR region@(Reg _ _ tunnels) city1 city2
  | connectedR region city1 city2 = minimum (map (\tunnel -> availableCapacityInTunnel tunnel) relevantTunnels)
  | otherwise = 0
  where
    relevantTunnels = filter (\tunnel -> connectsT city1 city2 tunnel) tunnels

availableCapacityInTunnel :: Tunel -> Int
availableCapacityInTunnel (Tun links) = minimum (map capacityL links)
-------------------Funcioines Propias---------------------


foundL :: Region -> Link -> Region
foundL (Reg cities links tunels) link = Reg cities (link:links) tunels

foundT :: Region -> Tunel -> Region
foundT (Reg cities links tunels) tunel = Reg cities links ( tunel:tunels)

linkPathCheck :: [City] -> [Link] -> Bool
linkPathCheck [] _ = False
linkPathCheck _ [] = False
linkPathCheck (x:xs) (y:ys) = if connectsL x y
                              then True 
                              else linkPathCheck (x:xs) ys

sameRegion :: Region -> City -> City -> Bool
sameRegion (Reg cities _ _) city1 city2 = elem city1 cities && elem city2 cities
