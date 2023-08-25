--Region.hs

module Region ( Region(Reg), newR, foundR, linkR, tunelR, connectedR, linkedR,
delayR, availableCapacityForR, foundL, foundT, sameRegion, minimumCapacity, 
linkExists, hasDuplicateCities)
   where
import City
import Link
import Tunel
import Quality

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)


newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunnels) city
  | any (\existingCity -> distanceC existingCity city == 0) cities = error "Una ciudad con las mismas coordenadas ya existe"
  | otherwise = Reg (city : cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region  -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR region@(Reg cities links tunnels) city1 city2 quality
--  | city1 `elem` cities && city2 `elem` cities =
  | sameRegion region city1 city2 =
      if linkExists city1 city2 links
        then error "El enlace ya existe en la región"
        else  Reg cities (newL city1 city2 quality : links) tunnels
  | otherwise = error "Las ciudades no existen en la región"

tunelR :: Region -> [City] -> Region  -- genera una comunicación entre dos ciudades distintas de la región
tunelR _ [] = error "La lista de ciudades está vacía. Debe proporcionar al menos dos ciudades."
tunelR _ [_] = error "La lista de ciudades contiene solo una ciudad. Debe proporcionar al menos dos ciudades."
tunelR region@(Reg citiesInRegion links tunnels) requestedCities
  | hasDuplicateCities requestedCities = error "La lista de ciudades contiene duplicados."
  | not allCitiesInRegion = error "No todas las ciudades proporcionadas existen en la región."
  | citiesConnectedWithTunnels = error "Las ciudades de los extermos ya estan connectas ya estan conectadas a traves de un túnel."
  | not citiesConnectedInOrder = error "No todas las ciudades están conectadas en el orden proporcionado."
  | not hasCapacityForLinks = error "No hay suficiente capacidad para todos los links entre las ciudades."
  | otherwise = Reg citiesInRegion links (newT tunnelLinks : tunnels)
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

    hasCapacityForLinks = all (\(c1, c2) -> (availableCapacityForR region c1 c2 ) > 0) (zip requestedCities (tail requestedCities))

    citiesConnectedWithTunnels =  any (\tunnel -> connectsT (head requestedCities) (last requestedCities) tunnel) tunnels

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están conectadas por un túnel
connectedR (Reg _ _ tunnels) city1 city2 = any (connectsT city1 city2) tunnels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están enlazadas
linkedR (Reg _ links _) city1 city2 = any (linksL city1 city2) links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region@(Reg _ _ tunnels) city1 city2
  | connectedR region city1 city2 = delayT (findTunnel city1 city2 tunnels)
  | otherwise = 0.0

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg cities links tunnels) city1 city2
    | not (city1 `elem` cities && city2 `elem` cities) = error "Las ciudades no existen en la región"
--    | not (sameRegion region city1 city2) = error "Las ciudades no existen en la región"
    | distanceC city1 city2 == 0 = error "Las ciudades son iguales"
    | otherwise = reduceCapacity capacity tunnels city1 city2
  where
    capacity = minimumCapacity (filter (linksL city1 city2) links)

    reduceCapacity :: Int -> [Tunel] -> City -> City -> Int
    reduceCapacity capacity [] _ _ = capacity
    reduceCapacity capacity (tunnel : rest) city1 city2 =
      case findLink city1 city2 links of
        Just link ->
            let reduction = if usesT link tunnel then 1 else 0
            in reduceCapacity (capacity - reduction) rest city1 city2
        Nothing ->
            reduceCapacity capacity rest city1 city2



-------------------Funcioines Propias---------------------

minimumCapacity :: [Link] -> Int
minimumCapacity [] = maxBound
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

hasDuplicateCities :: Eq a => [a] -> Bool
hasDuplicateCities [] = False
hasDuplicateCities (x:xs) = x `elem` xs || hasDuplicateCities xs

findLink :: City -> City -> [Link] -> Maybe Link
findLink _ _ [] = Nothing
findLink cityA cityB (link : rest)
      | linksL cityA cityB link = Just link
      | otherwise = findLink cityA cityB rest
