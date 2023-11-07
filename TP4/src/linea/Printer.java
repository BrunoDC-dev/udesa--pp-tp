package linea;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.stream.IntStream;

// https://www.lookuptables.com/text/extended-ascii-table

public class Printer {
    Dashboard board;

    public Printer(Dashboard board) {
        this.board = board;
    }

    public String show() {

        // ┌ ┬ ┐┾ ┿ ┽ ┼ ─ │
        String top = getLine("┌", "──", "┬", "┐");
        String horizontalLine = getLine("├", "──", "┼", "┤");
        String bottom = getLine("└", "──", "┴", "┘");

        // String body = IntStream.range(0, board.getHeight())
        //     .mapToObj(row -> IntStream.range(0, board.getWidth())
        //         .mapToObj(column -> board.getPieceAt(column, row))
        //         .collect(Collectors.joining("│", "│", "│\n")))
        //     .collect(Collectors.joining());
        
        String body = IntStream.range(0, board.getHeight())
            .mapToObj(row -> IntStream.range(0, board.getWidth())
                .mapToObj(column -> board.getPieceAt(column, board.getHeight() - row - 1))
                .collect(Collectors.joining("│", "│", "│\n")))
            .collect(Collectors.joining());

        String columnNumbers = "│" + 
            IntStream.range(1, board.getWidth()+ 1)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" │")) + " │\n";

        return top + body + horizontalLine + columnNumbers + bottom;

    }

    public String getLine(String left, String middle, String intersection, String right) {
        return left + IntStream.range(0, board.getWidth())
            .mapToObj(column -> middle)
            .collect(Collectors.joining(intersection)) + right + "\n";
    }
}