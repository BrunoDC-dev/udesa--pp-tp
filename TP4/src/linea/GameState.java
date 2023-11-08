package linea;
import java.util.ArrayList;
import java.util.List;
public abstract class GameState {
    protected Dashboard currentGame;
    protected ArrayList<GameState> State;
    public  GameState (Dashboard currentGame){
        this.currentGame = currentGame;
    }
    public abstract void playWhiteAt(int columnNumber);
    public abstract void playBlackAt(int columnNumber);
    public abstract boolean isValid();
    public abstract boolean isPlayingWhite();

    public abstract boolean isPlayingBlack();

    public abstract boolean isFinished();

    public abstract String show();


    public GameState selecState (){
        ArrayList<GameState> states = new ArrayList<GameState>(List.of(
                new PlayingWhite(currentGame), new PlayingBlack(currentGame),
                new WhiteWinState(currentGame), new BlackWinState(currentGame),
                new DrawState(currentGame)
                ));
        return states.stream().filter(state -> state.isValid()).findFirst().get();
    }
}
