package linea;

public class DrawState extends GameState {
    public DrawState(Linea currentGame) {
        super(currentGame);
    }

    public void playRedAt(int column ) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public void playBlueAt(int column) {
        throw new RuntimeException(Linea.canNotPlayWhenGameIsOverErrorMessage);
    }

    public boolean isValid() {
        return currentGame.isFull() && !currentGame.hasRedWon() && !currentGame.hasBlueWon();
    }

    public boolean isFinished() {
        return true;
    }

    public boolean isADraw() {
        return true;
    }
    
    public String show() {
        return "Game is a draw ";
    }
}
