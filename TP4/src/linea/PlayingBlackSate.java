package linea;

public class PlayingBlackSate extends GameState {

    public PlayingBlackSate(Linea currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
        throw new RuntimeException(Linea.notWhiteTurnErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
        currentGame.addPieceAt(columnNumber, Linea.Black);
        currentGame.modifyState(selecState());
    }

    public boolean isValid() {
        return !currentGame.isAdraw()
            && !currentGame.hasWhiteWon()
            && !currentGame.hasBlackWon()
            && !currentGame.isPlayingBlack()
            && currentGame.isPlayingWhite();
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
        return " Playing Black ";
    }
}
