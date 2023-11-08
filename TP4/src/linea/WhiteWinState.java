package linea;

public class WhiteWinState extends GameState {
    public WhiteWinState(Dashboard currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
        throw new RuntimeException(Dashboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
        throw new RuntimeException(Dashboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return !currentGame.hasWhtieWon();
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
        return "White wins";
    }
}
