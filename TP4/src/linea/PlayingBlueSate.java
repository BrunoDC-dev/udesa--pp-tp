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
