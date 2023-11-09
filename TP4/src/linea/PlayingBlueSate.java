package linea;

public class PlayingBlueSate extends GameState {

    public PlayingBlueSate(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt(int columnNumber) {
        throw new RuntimeException(Linea.notWhiteTurnErrorMessage);
    }

    public void playBlueAt(int columnNumber) {
        currentGame.addPieceAt(columnNumber, Linea.Blue);
        currentGame.modifyState(selecState());
    }

    public boolean isValid() {
        return !currentGame.isAdraw()
            && !currentGame.hasRedWon()
            && !currentGame.hasBlueWon()
            && !currentGame.isPlayingBlue()
            && currentGame.isPlayingRed();
    }

    public boolean isPlayingRed() {
        return false;
    }

    public boolean isPlayingBlue() {
        return true;
    }

    public boolean isFinished() {
        return false;
    }

    public String show() {
        return " Playing Blue ";
    }
}
