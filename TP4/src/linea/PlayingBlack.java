package linea;

public class PlayingBlack extends GameState {

    public PlayingBlack(Dashboard currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
            throw new RuntimeException(Dashboard.notWhiteTurnErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
       currentGame.addPieceAt(columnNumber, Dashboard.Black);
       currentGame.modifyState(selecState());
    }

    public boolean isValid() {
        return !currentGame.isAdraw() && !currentGame.hasWhtieWon() && !currentGame.hasBlackWon()
        && !currentGame.isPlayingBlack() && currentGame.isPlayingWhite();
    }

    public boolean isPlayingWhite() {   
        return false;
    
    }
    public boolean isPlayingBlack() {
        return true;
    }

    public boolean isFinished() {
        return false;
    }
    public String show() {
        return "Playing Black";
    }
}
