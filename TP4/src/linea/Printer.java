package linea;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Printer {

    Linea board;

    public Printer(Linea board) {
        this.board = board;
    }

    public String show() {
        String top = getLine("┌", "──", "┬", "┐");
        String horizontalLine = getLine("├", "──", "┼", "┤");
        String bottom = getLine("└", "──", "┴", "┘");

        String body = IntStream.range(0, board.getHeight())
            .mapToObj(row -> IntStream.range(0, board.getWidth())
                .mapToObj(column -> board.getPieceAt(column, board.getHeight() - row - 1))
                .collect(Collectors.joining("│", "│", "│\n")))
            .collect(Collectors.joining());

        String columnNumbers = "│" +
            IntStream.range(1, board.getWidth() + 1)
                .mapToObj(column -> column >= 10 ?  Integer.toString(column) :  column + " ")
                .collect(Collectors.joining("│")) + "│\n";

        String gameMode = "GameMode: " + this.board.getGameMode() + "\n";
        String current_state = "<" + this.board.getState().show() + ">\n";

        return top + body + horizontalLine + columnNumbers + bottom +gameMode + current_state;

    }

    public String getLine(String left, String middle, String intersection, String right) {
        return left + IntStream.range(0, board.getWidth())
            .mapToObj(column -> middle)
            .collect(Collectors.joining(intersection)) + right + "\n";
    }
}