package linea;
import java.util.ArrayList;
import java.util.List;
public abstract class GameState {
    protected Linea currentGame;
    protected ArrayList<GameState> State;

    public GameState(Linea currentGame) {
        this.currentGame = currentGame;
    }
    
    public abstract String show();
    public abstract void playRedAt(int column);
    public abstract void playBlueAt(int column);
    public abstract boolean isValid();
    
    public boolean isPlayingRed() {
        return false;
    };

    public boolean isPlayingBlue() {
        return false;
    };

    public boolean isFinished(){
        return false;
    };
    
    public boolean isADraw(){
        return false;
    };

    public boolean redWon(){
        return false;
    };
    
    public boolean blueWon(){
        return false;
    };


    public GameState selecState (){
        ArrayList<GameState> states = new ArrayList<GameState>(List.of(
                new PlayingRedSate(currentGame), new PlayingBlueSate(currentGame),
                new RedWinState(currentGame), new BlueWinState(currentGame),
                new DrawState(currentGame)
        ));
                
        return states.stream().filter(state -> state.isValid()).findFirst().get();
    }
}
