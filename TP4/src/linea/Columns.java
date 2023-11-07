package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Columns {
    ArrayList<String> slots = new ArrayList<String>();
    int columnNumber;
    int maxHeigth;
    String slotErrorMessage = "No empty slots";
    String emptySlot = "  ";

    public Columns(int height , int columnNumber){
        this.columnNumber = columnNumber;
        this.maxHeigth = height;
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
        return slots.stream()
        .skip(row)
        .findFirst()
        .map(String::valueOf)
        .orElse(emptySlot);
    }
    public boolean winnerInColumn (String piece){
        return IntStream.range(0, slots.size() - 3)
            .anyMatch(row -> IntStream.range(0, 4)
            .allMatch(k -> slots.get(row + k).equals(piece)));
    }
}
