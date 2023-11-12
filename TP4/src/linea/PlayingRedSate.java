package linea;

public class PlayingRedSate  extends GameState{
    public PlayingRedSate(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt(int column) {
        currentGame.addPieceAt(column , Linea.Red);
        currentGame.modifyState(selecState());
    }

    public void playBlueAt(int column) {
        throw new RuntimeException(Linea.notBlueTurnErrorMessage);
    }

    public boolean isValid() {
        return !currentGame.isFull()
            && !currentGame.hasRedWon()
            && !currentGame.hasBlueWon()
            && currentGame.isPlayingBlue()
            && !currentGame.isPlayingRed();
    }

    public boolean isPlayingRed() {   
        return true;
    }
    
    public String show() {
        return " Playing Red ";
    }
}
