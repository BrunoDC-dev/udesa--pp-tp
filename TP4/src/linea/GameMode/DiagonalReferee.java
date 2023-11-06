package linea.GameMode;

import linea.Dashoboard;

public class DiagonalReferee extends Referee{
    public DiagonalReferee(){
       super(  "C" );
    }
    public boolean anyoneWon (Dashoboard dashoboard,String piece){
       return dashoboard.anyoneWonDiagonal(piece);
    }
}
