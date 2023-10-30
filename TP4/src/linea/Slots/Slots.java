package linea.Slots;

import linea.Pieces.Piece;

public abstract class Slots {
    public abstract boolean isEmpty ();
    public abstract String show ();
    public abstract boolean hasBlackPiece ();
    public abstract boolean hasWhitePiece ();
    public abstract Slots place(Piece piece);
    public abstract Piece getPiece();
}
