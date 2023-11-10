package linea;

public class PlayingRedSate  extends GameState{
    public PlayingRedSate(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt( ) {
        return; 
    }

    public void playBlueAt() {
        throw new RuntimeException(Linea.notBlueTurnErrorMessage);
    }

    public boolean isValid() {
        return !currentGame.isAdraw()
            && !currentGame.hasRedWon()
            && !currentGame.hasBlueWon()
            && currentGame.isPlayingBlue()
            && !currentGame.isPlayingRed();
    }

    public boolean isPlayingRed() {   
        return true;
    }

    public boolean isPlayingBlue() {
        return false;
    }

    public boolean isFinished() {
        return false;
    }
    
    public String show() {
        return " Playing Red ";
    }
}
