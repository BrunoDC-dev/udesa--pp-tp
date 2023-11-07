package linea.GameMode;

import linea.Dashboard;

public class DiagonalReferee extends Referee{
    public DiagonalReferee(){
       super( 'B' );
    }
    public boolean anyoneWon (Dashboard dashoboard,String piece){
       return dashoboard.anyoneWonDiagonal(piece);
    }
}
