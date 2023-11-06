package linea;

public class PlayingBlack extends GameState {

    public PlayingBlack(Dashoboard currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
            throw new RuntimeException(Dashoboard.notWhiteTurnErrorMessage);
    }

    public void playBlackAt(int columnNumber) {
       currentGame.addPieceAt(columnNumber, Dashoboard.Black);
       currentGame.hasBlackWon();
       currentGame.modifyState(selecState());
    }

    public boolean isValid() {
        return !currentGame.isFull() && !currentGame.finished() && !currentGame.isPlayingBlack()
                && currentGame.isPlayingWhite();
    }

    public boolean isPlayingWhite() {   
        return false;
    
    }
    public boolean isPlayingBlack() {
        return true;
    }
    public boolean isFinished() {
        return false;
    }
}
