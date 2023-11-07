package linea.GameMode;

import linea.Dashboard;

public class VerticalReferee extends Referee {
    public VerticalReferee(){
       super(  'A' );
    }
    public boolean anyoneWon (Dashboard dashoboard , String player){
        return dashoboard.anyoneWonVertical(player) || dashoboard.anyoneWonHorizontal(player);
    }
}
