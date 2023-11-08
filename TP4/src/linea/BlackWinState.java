package linea;

public class BlackWinState  extends GameState{
       public BlackWinState(Dashboard currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
        throw new RuntimeException(Dashboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
        throw new RuntimeException(Dashboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.hasBlackWon();
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
        return "  Black wins   ";
    }
}
