package linea;

public class PlayingWhite  extends GameState{
    public PlayingWhite(Dashboard currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber ) {
        currentGame.addPieceAt(columnNumber, Dashboard.White);
        currentGame.modifyState(selecState());
    }

    public void playBlackAt(int columnNumber) {
        throw new RuntimeException(Dashboard.notBlackTurnErrorMessage);
    }

    public boolean isValid() {
        return !currentGame.isAdraw() && !currentGame.hasWhiteWon() && !currentGame.hasBlackWon()
        && currentGame.isPlayingBlack() && !currentGame.isPlayingWhite();
    }

    public boolean isPlayingWhite() {   
        return true;
     
    }

    public boolean isPlayingBlack() {
        return false;
    }

    public boolean isFinished() {
        return false;
    }
    
    public String show() {
        return " Playing White ";
    }
}
