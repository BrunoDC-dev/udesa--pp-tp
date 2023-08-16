--Region.hs


module Region ( Region, newR, foundR, linkR, --tunelR
pathR, linksForR, connectedR, linkedR, --delayR, availableCapacityForR, usedCapacityForR
foundL, foundT)
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
linkR (Reg cities links tunels) city1 city2 quality = Reg cities (newL city1 city2 quality:links) tunels

pathR :: Region -> City -> City -> [City] -- indica el camino entre dos ciudades de la región
pathR (Reg cities links tunels) city1 city2 = if connectedR (Reg cities links tunels) city1 city2 then [city1,city2] else pathR (Reg cities links tunels) city1 city2

linksForR :: Region -> City -> [Link] -- indica los enlaces de la región que parten desde la ciudad indicada
linksForR (Reg cities [] tunels) city = []
linksForR (Reg cities (link:rest) tunels) city = if connectsL city link
   then link:linksForR (Reg cities rest tunels) city else linksForR (Reg cities rest tunels) city

--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región



-- verificar que esten enlazados




connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links []) city1 city2 = False
connectedR (Reg cities links (tunel:rest)) city1 city2 = connectsT city1 city2  tunel|| connectedR (Reg cities links rest) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg cities [] tunels) city1 city2 = False
linkedR (Reg cities (link:rest) tunels) city1 city2 = linksL city1 city2 link || linkedR (Reg cities rest tunels) city1 city2

---Tunnnell
--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora


--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades



-------------------Funcioines Propias---------------------


foundL :: Region -> Link -> Region
foundL (Reg cities links tunels) link = Reg cities (link:links) tunels

foundT :: Region -> Tunel -> Region
foundT (Reg cities links tunels) tunel = Reg cities links ( tunel:tunels)