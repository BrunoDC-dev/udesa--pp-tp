package linea.GameMode;

import linea.Dashoboard;

public class HorizontalReferee extends Referee {
    public HorizontalReferee(){
       super(  "B" );
    }
    public void anyoneWon (Dashoboard dashoboard){
        dashoboard.anyoneWonHorizontal();
    }
}
