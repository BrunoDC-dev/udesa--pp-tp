package linea;

public class GameOver extends GameState {
    public GameOver(Dashoboard currentGame ) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
            throw new RuntimeException(Dashoboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
      throw new RuntimeException(Dashoboard.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.finished() || currentGame.isAdraw();
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
}
