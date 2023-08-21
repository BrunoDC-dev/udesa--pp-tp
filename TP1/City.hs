-- City.hs
module City ( City(Cit), newC, nameC, distanceC ) where
import Point

data City = Cit String Point deriving (Eq, Show, Ord)

newC :: String -> Point -> City
newC name point = Cit name point

nameC :: City -> String 
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ point1) (Cit _ point2) = difP point1 point2
