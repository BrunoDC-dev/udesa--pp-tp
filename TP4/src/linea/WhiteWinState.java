package linea;

public class WhiteWinState extends GameState {
    public WhiteWinState(Linea currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.hasWhiteWon();
    }

    public boolean isPlayingWhite() {   
        return false;
    }

    public boolean isPlayingBlack() {
        return false;
    }

    public boolean isFinished() {
        return true;
    }

    public String show() {
        return "  White wins   ";
    }
}
