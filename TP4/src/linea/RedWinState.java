package linea;

public class RedWinState extends GameState {
    public RedWinState(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt() {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlueAt() {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.hasRedWon();
    }

    public boolean isFinished() {
        return true;
    }
    
    public boolean redWon() {
        return true;
    }

    public String show() {
        return "  Red wins   ";
    }
}
