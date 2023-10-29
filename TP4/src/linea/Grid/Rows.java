package linea.Grid;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import linea.Pieces.BlackPiece;
import linea.Pieces.WhitePiece;
import linea.Slots.EmptySlot;
import linea.Slots.Slots;

public class Rows {
    ArrayList<Slots> slots = new ArrayList<Slots>();
    int rowNumber;
    public Rows(int width , int rowNumber){
        slots = IntStream.range(0, width)
                .mapToObj(i -> new EmptySlot())
                .collect(Collectors.toCollection(ArrayList::new));
        this.rowNumber = rowNumber;
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
    public void playWhiteAt(int position ) {
        Slots slot = slots.get(position).place(new WhitePiece());
        slots.set(position, slot);
    }
    public void playBlackAt(int position) {
        Slots slot =slots.get(position).place(new BlackPiece());
        slots.set(position, slot);
    }
    public int getRowNumber() {
        return rowNumber;
    }
    public boolean winnerInRow(){
        return winnerIsWhite() || winnerIsBlack();
    }
    public boolean winnerIsWhite() {
        return IntStream.range(0, slots.size() - 3)
            .anyMatch(i -> slots.get(i).hasWhitePiece() && slots.get(i + 1).hasWhitePiece()
                && slots.get(i + 2).hasWhitePiece() && slots.get(i + 3).hasWhitePiece());
    }
    public boolean winnerIsBlack(){
        return IntStream.range(0, slots.size() - 3)
            .anyMatch(i -> slots.get(i).hasBlackPiece() && slots.get(i + 1).hasBlackPiece()
                && slots.get(i + 2).hasBlackPiece() && slots.get(i + 3).hasBlackPiece());
    }
}
