package linea;

public class VerticalHorizontalMode extends GameMode {

    public VerticalHorizontalMode() {
        super('A');
    }

    public boolean anyoneWon(Linea dashoboard, String player) {
        return dashoboard.anyoneWonVertical(player)
            || dashoboard.anyoneWonHorizontal(player);
    }

    public char show() {
        return 'A'	;
    }
}
