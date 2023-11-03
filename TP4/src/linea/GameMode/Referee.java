package linea.GameMode;

import java.util.List;

import linea.Dashoboard;

public abstract class Referee {
    String type;
    public static List<Referee> referees = List.of(
        new VerticalReferee(),
        new HorizontalReferee(),
        new DiagonalReferee()
    );
    public Referee(String type){
        this.type = type;
    }
    public abstract void anyoneWon(Dashoboard dashoboard , String player);
    
    public  boolean canHandle(String type){
        return this.type ==type;
    };
    
    public static Referee getReferee(String type){
      return referees.stream()
        .filter(referee -> referee.canHandle(type))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No referee found for type: " + type));
    }
}
