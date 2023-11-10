package linea;

public class BlueWinState  extends GameState{
       public BlueWinState(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt() {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlueAt() {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.hasBlueWon();
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
        return "  Blue wins   ";
    }
}
