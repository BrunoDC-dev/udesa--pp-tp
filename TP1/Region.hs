--Region.hs


module Region ( Region(Reg), newR, foundR, linkR, tunelR, connectedR,linkedR,
delayR, availableCapacityForR,foundL, foundT,
sameRegion, minimumCapacity, linkExists)
   where
import City
import Link
import Tunel
import Quality

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunnels) city
  | any (\existingCity -> distanceC existingCity city == 0) cities = error "Una ciudad con las mismas coordenadas ya existe"
  | otherwise = Reg (city : cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region
linkR (Reg cities links tunnels) city1 city2 quality
  | city1 `elem` cities && city2 `elem` cities =
      if linkExists city1 city2 links
        then error "El enlace ya existe en la región"
        else  Reg cities (newL city1 city2 quality : links) tunnels
  | otherwise = error "Las ciudades no existen en la región"

tunelR :: Region -> [City] -> Region
tunelR _ [] = error "La lista de ciudades está vacía. Debe proporcionar al menos dos ciudades."
tunelR _ [_] = error "La lista de ciudades contiene solo una ciudad. Debe proporcionar al menos dos ciudades."
tunelR (Reg citiesInRegion links tunnels) requestedCities
  | hasDuplicateCities requestedCities = error "La lista de ciudades contiene duplicados."
  | allCitiesInRegion && citiesConnectedInOrder = Reg citiesInRegion links (newT tunnelLinks : tunnels)
  | otherwise = error errorMessage
  where
    allCitiesInRegion = all (`elem` citiesInRegion) requestedCities
    (citiesConnectedInOrder, tunnelLinks) = checkOrder requestedCities links []

    checkOrder :: [City] -> [Link] -> [Link] -> (Bool, [Link])
    checkOrder [] _ acc = (True, reverse acc)
    checkOrder [_] _ acc = (True, reverse acc)
    checkOrder (city1 : city2 : rest) linkList acc =
      case findLink city1 city2 linkList of
        Just link -> checkOrder (city2 : rest) linkList (link : acc)
        Nothing -> (False, [])

    findLink :: City -> City -> [Link] -> Maybe Link
    findLink _ _ [] = Nothing
    findLink cityA cityB (link : rest)
      | linksL cityA cityB link = Just link
      | otherwise = findLink cityA cityB rest

    errorMessage = "No todas las ciudades están conectadas en el orden proporcionado."



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
    | distanceC city1 city2 == 0 = error "Las ciudades son iguales"
    | otherwise = reduceCapacity capacity tunnels city1 city2
  where
    capacity = minimumCapacity (filter (linksL city1 city2) links)

    reduceCapacity :: Int -> [Tunel] -> City -> City -> Int
    reduceCapacity capacity [] _ _ = capacity
    reduceCapacity capacity (tunnel : rest) city1 city2 =
        let link = findLinkConnectingCities city1 city2 links
            reduction = if usesT link tunnel then 1 else 0
        in reduceCapacity (capacity - reduction) rest city1 city2

    findLinkConnectingCities :: City -> City -> [Link] -> Link
    findLinkConnectingCities city1 city2 links = head [link | link <- links, linksL city1 city2 link]


-------------------Funcioines Propias---------------------

minimumCapacity :: [Link] -> Int
minimumCapacity [] = maxBound
-- minimumCapacity (Lin _ _ (Qua _ capacity _) : rest) = min capacity (minimumCapacity rest)

minimumCapacity links = minimum (map capacityL links)

linkExists :: City -> City -> [Link] -> Bool
linkExists _ _ [] = False
linkExists c1 c2 (link : rest)
      | linksL c1 c2  link = True
      | otherwise = linkExists c1 c2 rest

findTunnel :: City -> City -> [Tunel] -> Tunel
findTunnel city1 city2 (tunnel@( links):rest)
  | connectsT city1 city2 tunnel = tunnel
  | otherwise = findTunnel city1 city2 rest

foundL :: Region -> Link -> Region
foundL (Reg c links t) link = Reg c (link:links) t

foundT :: Region -> Tunel -> Region
foundT (Reg c l tunels) tunel = Reg c l (tunel:tunels)


sameRegion :: Region -> City -> City -> Bool
sameRegion (Reg cities _ _) city1 city2 = elem city1 cities && elem city2 cities

hasDuplicateCities :: Eq a => [a] -> Bool
hasDuplicateCities [] = False
hasDuplicateCities (x:xs) = x `elem` xs || hasDuplicateCities xs

allLinksConnectedInOrder :: [City] -> [Link] -> Bool
allLinksConnectedInOrder cities links = all (\(city1, city2) -> linksExist city1 city2) connections
  where
    connections = zip cities (tail cities)
    
    linksExist city1 city2 = any (linksL city1 city2) links || any (linksL city2 city1) links
