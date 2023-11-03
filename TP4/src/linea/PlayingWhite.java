package linea;

public class PlayingWhite  extends GameState{
    public PlayingWhite(Dashoboard currentGame) {
        super(currentGame);
    }

    public void playWhiteAt(int columnNumber) {
        currentGame.addPieceAt(columnNumber, Dashoboard.White);
        currentGame.hasWon(Dashoboard.White);
        currentGame.modifyState(selecState());
    }

    public void playBlackAt(int columnNumber) {
        throw new RuntimeException(Dashoboard.notBlackTurnErrorMessage);
    }

    public boolean isValid() {
        return !currentGame.isFull() && !currentGame.finished()&& currentGame.isPlayingBlack()&& !currentGame.isPlayingWhite();
    }

    public boolean isPlayingWhite() {   
        return true;
     
    }

    public boolean isPlayingBlack() {
        return false;
    }
    public boolean isFinished() {
        return false;
    }
}
