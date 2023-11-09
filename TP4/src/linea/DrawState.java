package linea;

public class DrawState extends GameState {
    public DrawState(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt(int columnNumber ) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlueAt(int columnNumber) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.isAdraw() && !currentGame.hasRedWon() && !currentGame.hasBlueWon();
    }

    public boolean isPlayingRed() {   
        return false;
    }

    public boolean isPlayingBlue() {
        return false;
    }

    public boolean isFinished() {
        return true;
    }
    
    public String show() {
        return "Game is a draw ";
    }
}
