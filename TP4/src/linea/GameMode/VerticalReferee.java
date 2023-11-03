package linea.GameMode;

import linea.Dashoboard;

public class VerticalReferee extends Referee {
    public VerticalReferee(){
       super(  "A" );
    }
    public void anyoneWon (Dashoboard dashoboard , String player){
        dashoboard.anyoneWonVertical(player);
    }
}
