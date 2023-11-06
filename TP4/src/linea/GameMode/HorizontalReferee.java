package linea.GameMode;

import linea.Dashoboard;

public class HorizontalReferee extends Referee {
    public HorizontalReferee(){
       super(  'B' );
    }
    public boolean anyoneWon (Dashoboard dashoboard, String piece){
        return dashoboard.anyoneWonHorizontal(piece);
    }
}
