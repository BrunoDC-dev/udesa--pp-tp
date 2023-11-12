package linea;

public class BlueWinState  extends GameState{
       public BlueWinState(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt(int column) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlueAt(int column) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.hasBlueWon();
    }

    public boolean isFinished() {
        return true;
    }
    
    public boolean blueWon() {
        return true;
    }

    public String show() {
        return "  Blue wins   ";
    }
}
