package linea;

public class PlayingBlueSate extends GameState {

    public PlayingBlueSate(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt(int column) {
        throw new RuntimeException(Linea.notRedsTurnErrorMessage);
    }

    public void playBlueAt(int column) {
        currentGame.addPieceAt(column , Linea.Blue);
        currentGame.modifyState(selecState());
    }

    public boolean isValid() {
        return !currentGame.isFull()
            && !currentGame.hasRedWon()
            && !currentGame.hasBlueWon()
            && !currentGame.isPlayingBlue()
            && currentGame.isPlayingRed();
    }

    public boolean isPlayingBlue() {
        return true;
    }

    public String show() {
        return " Playing Blue ";
    }
}
