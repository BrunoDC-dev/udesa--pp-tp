package linea.Grid;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import linea.Pieces.BlackPiece;
import linea.Pieces.Piece;
import linea.Pieces.WhitePiece;
import linea.Slots.EmptySlot;
import linea.Slots.Slots;

public class Columns {
    ArrayList<Slots> slots = new ArrayList<Slots>();
    int columnNumber;
    String slotErrorMessage = "No empty slots";

    public Columns(int height , int columnNumber){
        slots = IntStream.range(0, height)
                .mapToObj(i -> new EmptySlot())
                .collect(Collectors.toCollection(ArrayList::new));
        this.columnNumber = columnNumber;
    }
    public String show() {
        return "";//TODO	
    }
    public boolean isEmpty() {
        return slots.stream().allMatch(Slots::isEmpty);
    }
    public boolean isFull() {
        return slots.stream().allMatch(slot -> !slot.isEmpty());
    }
    public int playWhiteAt() {
        int index = IntStream.range(0, slots.size())
                .filter(i -> slots.get(i).isEmpty())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(slotErrorMessage));
        Slots slot = slots.get(index).place(new WhitePiece());
        slots.set(index, slot);
        return index;
    }
    public int playBlackAt() {
        int index = IntStream.range(0, slots.size())
                .filter(i -> slots.get(i).isEmpty())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(slotErrorMessage));
        Slots slot = slots.get(index).place(new BlackPiece());
        slots.set(index, slot);
        return index;
    }
    public boolean winnerInColumn() {
        return winnerIsWhite() || winnerIsBlack();
    }
    public boolean winnerIsWhite (){
         return IntStream.range(0, slots.size() - 3)
            .anyMatch(i -> slots.get(i).hasWhitePiece() && slots.get(i + 1).hasWhitePiece()
                && slots.get(i + 2).hasWhitePiece() && slots.get(i + 3).hasWhitePiece());
    }
    public boolean winnerIsBlack (){
        return IntStream.range(0, slots.size() - 3)
            .anyMatch(i -> slots.get(i).hasBlackPiece() && slots.get(i + 1).hasBlackPiece()
                && slots.get(i + 2).hasBlackPiece() && slots.get(i + 3).hasBlackPiece());
    }
    public int getAmountOfPieces(){
        return (int) slots.stream().filter(slot -> !slot.isEmpty()).count();
    }
    public int getColumnNumber() {
        return columnNumber;
    }
    public Piece getPieceAt(int row) {
        return slots.get(row).getPiece();
    }
}
