--Region.hs


module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where
import City
import Link 
import Tunel
import Quality

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city = Reg (city:cities) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality = Reg cities ((newL city1 city2 quality):links) tunels

--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--tunelR (Reg cities links tunels) (citiesArray:rest) = Reg cities links ((newT [newl cititesArray]):tunels)
-- verificar que esten enlazados
connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links []) city1 city2 = False
connectedR (Reg cities links (tunel:rest)) city1 city2 = (connectsT tunel city1 city2) || (connectedR (Reg cities links rest) city1 city2)

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg cities [] tunels) city1 city2 = False
linkedR (Reg cities (link:rest) tunels) city1 city2 = (linksl city1 city2 linksl) || (linkedR (Reg cities rest tunels) city1 city2)

---Tunnnell
delayR :: R egion -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora


availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
