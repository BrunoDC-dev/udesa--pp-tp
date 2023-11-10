package linea;

public class DiagonalMode extends GameMode{
    public DiagonalMode(){
       super( 'B' );
    }
    public boolean anyoneWon (Linea dashoboard,String piece){
       return dashoboard.anyoneWonDiagonal(piece);
    }
}
