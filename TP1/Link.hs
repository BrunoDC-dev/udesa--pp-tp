--Link.hs

module Link ( Link, newL, linksL, connectsL, capacityL, delayL ) where
import City
import Quality
import Point
data Link = Lin City City Quality deriving (Eq, Show)
 

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 q  | distanceC city1 city2 == 0 = error "no es posible crear un link con la misma ciudad"
                    | otherwise = Lin city1 city2 q

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 _) = city == city1 || city == city2

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 (Lin city3 city4 _)  | distanceC city1 city2 == 0 = error "no es posible crear un link con la misma ciudad"
                                        | otherwise = elem city1 [city3, city4] && elem city2 [city3, city4]

capacityL :: Link -> Int
capacityL (Lin city1 city2 quailty) = capacityQ quailty

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 quality) = (distanceC city1 city2) * (delayQ quality) 