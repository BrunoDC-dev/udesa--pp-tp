package linea;

public class CombineMode extends GameMode {
    public CombineMode(){
       super(  'C' );
    }
    public boolean anyoneWon (Linea dashoboard, String piece){
        return dashoboard.anyoneWonHorizontal(piece)
            || dashoboard.anyoneWonVertical(piece) 
            || dashoboard.anyoneWonDiagonal(piece);
    }
}
