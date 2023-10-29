package linea.GameMode;

import linea.Dashoboard;

public class DiagonalReferee extends Referee{
    public DiagonalReferee(){
       super(  "C" );
    }
    public void anyoneWon (Dashoboard dashoboard){
        dashoboard.anyoneWonDiagonal();
    }
}
