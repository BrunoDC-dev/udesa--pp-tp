package linea.GameMode;

import linea.Dashoboard;

public class VerticalReferee extends Referee {
    public VerticalReferee(){
       super(  "A" );
    }
    public boolean anyoneWon (Dashoboard dashoboard , String player){
        return dashoboard.anyoneWonVertical(player);
    }
}
