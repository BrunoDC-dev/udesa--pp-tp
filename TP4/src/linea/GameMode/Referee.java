package linea.GameMode;

import java.util.List;

import linea.Dashboard;

public abstract class Referee {
    char type;
    public static List<Referee> referees = List.of(
        new VerticalReferee(),
        new AllPosibilitiesReferee(),
        new DiagonalReferee()
    );
    public Referee(char type){
        this.type = type;
    }
    public abstract boolean anyoneWon(Dashboard dashoboard , String player);
    
    public  boolean canHandle(char type){
        return this.type ==type;
    };
    
    public static Referee getReferee(char type) {
        return referees.stream()
                .filter(referee -> referee.canHandle(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No referee found for type: " + type));
    }
    public char getType(){
        return this.type;
    }
}
