--Region.hs


module Region ( Region, newR, foundR, linkR, tunelR, connectedR,linkedR, 
delayR, availableCapacityForR,foundL, foundT,findShortestPath)
   where
import City 
import Link 
import Tunel
import Quality
import GHC.Exts.Heap (GenClosure(link))
import Data.Maybe (fromJust)
import Data.List (sortOn)
import Data.Maybe (listToMaybe)
import Data.Function (on)
import Data.Map (Map)
import qualified Data.Map as Map
data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)
newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city = Reg (city:cities) links tunels

linkR :: Region -> City -> City -> Quality -> Region
linkR (Reg cities links tunnels) city1 city2 quality
  | city1 `elem` cities && city2 `elem` cities = 
      let newLink = newL city1 city2 quality
      in Reg cities (newLink : links) tunnels
  | otherwise = error "Las ciudades no existen en la región"






tunelR :: Region -> [City] -> Region
tunelR (Reg citiesRegion links tunnels) cities =
    let path = findShortestPath links (head cities) (last cities)
    in
    if path == Nothing
        then error "No se puede crear un tunel con las ciudades indicadas"
        else Reg citiesRegion links (newT (fromJust path) : tunnels)


connectedR :: Region -> City -> City -> Bool
connectedR (Reg _ _ tunnels) city1 city2 = any (\tunnel -> connectsT city1 city2 tunnel) tunnels

linkedR :: Region -> City -> City -> Bool
linkedR (Reg _ links _) city1 city2 = any (\link -> linksL city1 city2 link) links

delayR :: Region -> City -> City -> Float
delayR region@(Reg _ _ tunnels) city1 city2
  | connectedR region city1 city2 = delayT (findTunnel city1 city2 tunnels)
  | otherwise = 0.0


availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR region@(Reg _ _ tunnels) city1 city2
  | connectedR region city1 city2 = minimum (map (\tunnel -> availableCapacityInTunnel tunnel) relevantTunnels)
  | otherwise = 0
  where
    relevantTunnels = filter (\tunnel -> connectsT city1 city2 tunnel) tunnels

availableCapacityInTunnel :: Tunel -> Int
availableCapacityInTunnel (Tun links) = minimum (map capacityL links)
-------------------Funcioines Propias---------------------

findTunnel :: City -> City -> [Tunel] -> Tunel
findTunnel city1 city2 (tunnel@(Tun links):rest)
  | connectsT city1 city2 tunnel = tunnel
  | otherwise = findTunnel city1 city2 rest

foundL :: Region -> Link -> Region
foundL (Reg cities links tunels) link = Reg cities (link:links) tunels

foundT :: Region -> Tunel -> Region
foundT (Reg cities links tunels) tunel = Reg cities links ( tunel:tunels)


sameRegion :: Region -> City -> City -> Bool
sameRegion (Reg cities _ _) city1 city2 = elem city1 cities && elem city2 cities


----- bread first search siiiiiiiiiiiiiiiiiiiii



type Graph = Map City [Link]

bfs :: Graph -> City -> City -> Maybe [Link]
bfs graph startCity endCity = bfs' [(startCity, [])] Map.empty
  where
    bfs' :: [(City, [Link])] -> Map City Bool -> Maybe [Link]
    bfs' [] _ = Nothing
    bfs' ((city, path):rest) visited
      | city == endCity = Just (reverse path)
      | otherwise =
          case Map.lookup city visited of
            Just _ -> bfs' rest visited
            Nothing -> bfs' (rest ++ neighbors) (Map.insert city True visited)
      where
        neighbors = case Map.lookup city graph of
                      Just links -> [(toCity link, link:path) | link <- links]
                      Nothing -> []

        toCity (Lin _ c2 _) = c2

shortestPath :: Graph -> City -> City -> Maybe [Link]
shortestPath graph startCity endCity = bfs graph startCity endCity

findShortestPath :: [Link] -> City -> City -> Maybe [Link]
findShortestPath links startCity endCity = shortestPath graph startCity endCity
  where
    graph = buildGraph links

buildGraph :: [Link] -> Graph
buildGraph links = foldr addLinks Map.empty links
  where
    addLinks (Lin city1 city2 quality) graph =
      let link1 = Lin city1 city2 quality
          link2 = Lin city2 city1 quality
          graph' = Map.insertWith (++) city1 [link1] graph
      in Map.insertWith (++) city2 [link2] graph'

toCity :: Link -> City
toCity (Lin _ city _) = city

