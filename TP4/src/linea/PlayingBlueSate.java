package linea;

public class PlayingBlueSate extends GameState {

    public PlayingBlueSate(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt() {
        throw new RuntimeException(Linea.notRedsTurnErrorMessage);
    }

    public void playBlueAt() {
        return;
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
