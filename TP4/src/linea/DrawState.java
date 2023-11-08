package linea;

public class DrawState extends GameState {
    public DrawState(Dashboard currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber ) {
        throw new RuntimeException(Dashboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
        throw new RuntimeException(Dashboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.isAdraw() && !currentGame.hasWhiteWon() && !currentGame.hasBlackWon();
    }

    public boolean isPlayingWhite() {   
        return false;
    }

    public boolean isPlayingBlack() {
        return false;
    }

    public boolean isFinished() {
        return false;
    }
    
    public String show() {
        return "Game is a draw ";
    }
}
