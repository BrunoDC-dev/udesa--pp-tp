package linea.GameMode;

import linea.Dashoboard;

public class DiagonalReferee extends Referee{
    public DiagonalReferee(){
       super(  "C" );
    }
    public void anyoneWon (Dashoboard dashoboard,String piece){
        dashoboard.anyoneWonDiagonal(piece);
    }
}
