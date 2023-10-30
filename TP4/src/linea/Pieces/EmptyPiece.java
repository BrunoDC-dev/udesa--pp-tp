package linea.Pieces;

public class EmptyPiece extends Piece {
    
    public boolean isBlackPiece() {
        return false;
    }
    public boolean isWhitePiece() {
        return false;
    }
    public String show() {
        return "O";
    }
}
