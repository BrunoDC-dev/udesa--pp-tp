package linea;
import java.util.ArrayList;
import java.util.List;
public abstract class GameState {
    protected Linea currentGame;
    protected ArrayList<GameState> State;

    public GameState(Linea currentGame) {
        this.currentGame = currentGame;
    }
    
    public abstract void playRedAt();
    public abstract void playBlueAt();
    public abstract boolean isValid();
    public abstract boolean isPlayingRed();
    public abstract boolean isPlayingBlue();
    public abstract boolean isFinished();
    public abstract String show();

    public GameState selecState (){
        ArrayList<GameState> states = new ArrayList<GameState>(List.of(
                new PlayingRedSate(currentGame), new PlayingBlueSate(currentGame),
                new RedWinState(currentGame), new BlueWinState(currentGame),
                new DrawState(currentGame)
        ));
                
        return states.stream().filter(state -> state.isValid()).findFirst().get();
    }
}
