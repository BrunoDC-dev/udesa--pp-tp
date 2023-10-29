package linea.Pieces;

public class WhitePiece extends Piece{
    public boolean isBlackPiece(){
        return false;
    }
    public boolean isWhitePiece(){
        return true;
    }
    public String show(){
        return "W";
    }
}
