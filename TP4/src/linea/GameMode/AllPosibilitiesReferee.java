package linea.GameMode;

import linea.Dashboard;

public class AllPosibilitiesReferee extends Referee {
    public AllPosibilitiesReferee(){
       super(  'C' );
    }
    public boolean anyoneWon (Dashboard dashoboard, String piece){
        return dashoboard.anyoneWonHorizontal(piece) || dashoboard.anyoneWonVertical(piece) || dashoboard.anyoneWonDiagonal(piece);
    }
}
