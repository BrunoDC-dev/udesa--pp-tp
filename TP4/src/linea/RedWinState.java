package linea;

public class RedWinState extends GameState {
    public RedWinState(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt(int columnNumber) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlueAt(int columnNumber) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.hasRedWon();
    }

    public boolean isPlayingRed() {   
        return false;
    }

    public boolean isPlayingBlue() {
        return false;
    }

    public boolean isFinished() {
        return true;
    }

    public String show() {
        return "  Red wins   ";
    }
}
