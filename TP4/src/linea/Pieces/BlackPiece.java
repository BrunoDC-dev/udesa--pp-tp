package linea.Pieces;

public class BlackPiece extends Piece {
    public boolean isBlackPiece(){
        return true;
    }
    public boolean isWhitePiece(){
        return false;
    }
    public String show(){
        return "B";
    }
}
