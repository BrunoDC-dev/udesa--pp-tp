package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Columns {
    ArrayList<String> slots = new ArrayList<String>();
    int columnNumber;
    int maxHeigth;
    String slotErrorMessage = "No empty slots";

    public Columns(int height , int columnNumber){
        this.columnNumber = columnNumber;
        this.maxHeigth = height;
    }
    public String show() {
        return "";//TODO	
    }
    public boolean isEmpty() {
        return slots.size() == 0;
    }
    public boolean isFull() {
        return slots.size() == maxHeigth;
    }
    public void addPieceAt(String piece) {
        if (isFull()) {
            throw new RuntimeException(slotErrorMessage);
        }
        slots.add(piece);
    }

    public int getAmountOfPieces(){
        return slots.size();
    }
    public int getColumnNumber() {
        return columnNumber;
    }
    public String getPieceAt(int row) {
        if (row >= slots.size() || row < 0) {
            return "";
        }
        return slots.get(row);
    }
    public boolean winnerInColumn (String piece){
        return IntStream.range(0, slots.size() - 3)
            .anyMatch(i -> slots.get(i).equals(piece) && slots.get(i + 1).equals(piece)
                && slots.get(i + 2).equals(piece) && slots.get(i + 3).equals(piece));
    }

}
