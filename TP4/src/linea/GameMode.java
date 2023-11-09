package linea;

import java.util.List;

public abstract class GameMode {
    char type;
    public static List<GameMode> referees = List.of(
        new VerticalHorizontalMode(),
        new CombineMode(),
        new DiagonalMode()
    );

    public GameMode(char type) {
        this.type = type;
    }
    
    public abstract boolean anyoneWon(Linea dashoboard , String player);

    public  boolean canHandle(char type){
        return this.type ==type;
    };
    
    public static GameMode getReferee(char type) {
        return referees.stream()
                .filter(referee -> referee.canHandle(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No referee found for type: " + type));
    }

    public char getType(){
        return this.type;
    }
}
