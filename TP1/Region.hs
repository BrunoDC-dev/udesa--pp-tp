--Region.hs


module Region ( Region(Reg), newR, foundR, linkR, tunelR, connectedR,linkedR,
delayR, availableCapacityForR,foundL, foundT,findShortestPath, lowerQuality,
sameRegion, minimumCapacity, linkExists)
   where
import City
import Link
import Tunel
import Quality
import Data.Maybe ( fromJust, listToMaybe,isNothing )
import Data.List ( sortOn, nub )
import Data.Function (on)
import Data.Map (Map)
import qualified Data.Map as Map

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunnels) newCity@(Cit newCityName newPoint)
  | any (\(Cit _ existingPoint) -> existingPoint == newPoint) cities = error "City with same coordinates already exists"
  | otherwise = Reg (newCity:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region
linkR (Reg cities links tunnels) city1 city2 quality
  | city1 `elem` cities && city2 `elem` cities =
      if linkExists city1 city2 links
        then error "El enlace ya existe en la región"
        else let newLink = newL city1 city2 quality
             in Reg cities (newLink : links) tunnels
  | otherwise = error "Las ciudades no existen en la región"


tunelR :: Region -> [City] -> Region
tunelR _ [] = error "La lista de ciudades está vacía. Debe proporcionar al menos dos ciudades."
tunelR _ [_] = error "La lista de ciudades contiene solo una ciudad. Debe proporcionar al menos dos ciudades."
tunelR (Reg citiesInRegion links tunnels) requestedCities
  | hasDuplicateCities requestedCities = error "La lista de ciudades contiene duplicados."
  | allCitiesInRegion && allLinksExist && citiesConnectedInOrder = Reg citiesInRegion (lowerQuality links tunnelLinks) (newT tunnelLinks: tunnels)
  | otherwise = error errorMessage
  where
      allCitiesInRegion = all (`elem` citiesInRegion) requestedCities
      
      allLinksExist
        = all (\ city -> any (connected city) links) requestedCities
      (citiesConnectedInOrder, tunnelLinks)
        = checkOrder requestedCities links []
      
      connected (Cit cityName _) (Lin cityA cityB _)
        = cityName == getCityName cityA || cityName == getCityName cityB
      getCityName (Cit cityName _) = cityName

      checkOrder [] _ acc = (True, reverse acc)
      checkOrder [_] _ acc = (True, reverse acc)
      checkOrder (city1 : city2 : rest) linkList acc
      
        = case findLink city1 city2 linkList of
            Just link -> checkOrder (city2 : rest) linkList (link : acc)
            Nothing -> (False, [])
      
      findLink _ _ [] = Nothing
      findLink cityA cityB ((Lin c1 c2 quality) : rest)
        | cityA == c1 && cityB == c2 = Just (Lin c1 c2 quality)
        | cityA == c2 && cityB == c1 = Just (Lin c2 c1 quality)
        | otherwise = findLink cityA cityB rest
      
      hasDuplicateCities xs = length xs /= length (nub xs)
      
      errorMessage
        = "No todas las ciudades están conectadas en el orden proporcionado."


lowerQuality :: [Link] -> [Link] -> [Link]
lowerQuality links1 links2 = filter (\(Lin _ _ qua) -> qualityGreaterThanZero qua) modifiedLinks
  where
    modifiedLinks = map lowerIfPresent links1

    lowerIfPresent :: Link -> Link
    lowerIfPresent link@(Lin src dest qua) =
      if linkPresent link links2
        then Lin src dest (lowerQualityOfQua qua)
        else link

    linkPresent :: Link -> [Link] -> Bool
    linkPresent link = any (\(Lin src dest _) -> (src, dest) == getLinkEndpoints link || (dest, src) == getLinkEndpoints link)

    getLinkEndpoints :: Link -> (City, City)
    getLinkEndpoints (Lin src dest _) = (src, dest)

    lowerQualityOfQua :: Quality -> Quality
    lowerQualityOfQua (Qua name intVal floatVal) =
      case intVal of
        0 -> Qua name intVal floatVal
        _ -> Qua name (intVal - 1) floatVal

    qualityGreaterThanZero :: Quality -> Bool
    qualityGreaterThanZero (Qua _ intVal _) = intVal > 0


connectedR :: Region -> City -> City -> Bool
connectedR (Reg _ _ tunnels) city1 city2 = any (connectsT city1 city2) tunnels

linkedR :: Region -> City -> City -> Bool
linkedR (Reg _ links _) city1 city2 = any (linksL city1 city2) links

delayR :: Region -> City -> City -> Float
delayR region@(Reg _ _ tunnels) city1 city2
  | connectedR region city1 city2 = delayT (findTunnel city1 city2 tunnels)
  | otherwise = 0.0


availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR (Reg cities links tunnels) city1 city2
  | not (city1 `elem` cities && city2 `elem` cities) = error "Las ciudades no existen en la región"
  | city1 == city2 = error "Las ciudades son iguales"
  | otherwise = case findShortestPath links city1 city2 of
    Nothing -> error "No path found between the cities"
    Just path -> minimumCapacity path


-------------------Funcioines Propias---------------------
minimumCapacity :: [Link] -> Int
minimumCapacity [] = maxBound
-- minimumCapacity (Lin _ _ (Qua _ capacity _) : rest) = min capacity (minimumCapacity rest)

minimumCapacity links = minimum (map capacityL links)

linkExists :: City -> City -> [Link] -> Bool
linkExists _ _ [] = False
linkExists c1 c2 (Lin city1 city2 _ : rest)
      | c1 == city1 && c2 == city2 || c1 == city2 && c2 == city1 = True
      | otherwise = linkExists c1 c2 rest

findTunnel :: City -> City -> [Tunel] -> Tunel
findTunnel city1 city2 (tunnel@(Tun links):rest)
  | connectsT city1 city2 tunnel = tunnel
  | otherwise = findTunnel city1 city2 rest

foundL :: Region -> Link -> Region
foundL (Reg c links t) link = Reg c (link:links) t

foundT :: Region -> Tunel -> Region
foundT (Reg c l tunels) tunel = Reg c l (tunel:tunels)


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
shortestPath = bfs

findShortestPath :: [Link] -> City -> City -> Maybe [Link]
findShortestPath links = shortestPath graph
  where
    graph = buildGraph links

buildGraph :: [Link] -> Graph
buildGraph = foldr addLinks Map.empty
  where
    addLinks (Lin city1 city2 quality) graph =
      let link1 = Lin city1 city2 quality
          link2 = Lin city2 city1 quality
          graph' = Map.insertWith (++) city1 [link1] graph
      in Map.insertWith (++) city2 [link2] graph'

toCity :: Link -> City
toCity (Lin _ city _) = city

crercTunel :: Region -> [City] -> Region
crercTunel (Reg citiesRegion links tunnels) cities@(city1 : city2 : _)
    | city1 == city2 = error "Las dos ciudades proporcionadas son iguales. Deben ser diferentes."
    | otherwise =
        let path = findShortestPath links city1 city2
        in
        case path of
            Nothing -> error "No se puede crear un túnel con las ciudades indicadas."
            Just newPath ->
                let updatedLinks = lowerQuality links newPath
                in Reg citiesRegion updatedLinks (newT newPath : tunnels)
