package linea.Slots;

import linea.Pieces.Piece;

public class EmptySlot extends Slots {

    public boolean isEmpty() {
        return true;
    }
    public String show() {
        return "O";
    }
    
    public boolean hasBlackPiece() {
        return false;
    }
    public boolean hasWhitePiece() {
        return false;
    }
    public Slots place(Piece piece) {
        return new PieceSlot(piece);
    }

}
