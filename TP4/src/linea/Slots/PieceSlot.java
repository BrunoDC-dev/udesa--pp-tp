package linea.Slots;

import linea.Pieces.Piece;

public class PieceSlot  extends Slots{
    private Piece piece;
    
    public PieceSlot(Piece piece){
        this.piece = piece;
    }
    
    public boolean isEmpty() {
        return false;
    }
    public String show() {
        return piece.show();
    }
    
    public boolean hasBlackPiece() {
        return piece.isBlackPiece();
    }
    public boolean hasWhitePiece() {
        return piece.isWhitePiece();
    }
    
    public Slots place(Piece piece) {
        throw new RuntimeException("No se puede colocar una pieza en un casillero ocupado");
    }
    public Piece getPiece() {
        return piece;
    }
}
