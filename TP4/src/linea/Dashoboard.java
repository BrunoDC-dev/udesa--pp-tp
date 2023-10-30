package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import linea.GameMode.Referee;
import linea.Grid.Columns;
import linea.Grid.Rows;
import linea.Pieces.BlackPiece;
import linea.Pieces.Piece;
import linea.Pieces.WhitePiece;
public class Dashoboard {
   
    int width;
    int height;
    String gameType;
   
    ArrayList<Columns> columns = new ArrayList<Columns>();
    ArrayList<Rows> rows = new ArrayList<Rows>();
   
    boolean gameIsOver ;
    
    public String columnErrorMessage = "No such column";
   
    public Dashoboard(int width, int height, String gameType){
        this.width = width;
        this.height = height;
        this.gameType = gameType;
        this.gameIsOver = false;
        columns = IntStream.range(0, width).mapToObj(i -> new Columns(height, i)).collect(Collectors.toCollection(ArrayList::new));
        rows = IntStream.range(0, height).mapToObj(i -> new Rows(width, i)).collect(Collectors.toCollection(ArrayList::new));
    }
    public boolean isEmpty(){
        return this.columns.stream().allMatch(column -> column.isEmpty()) && this.rows.stream().allMatch(row -> row.isEmpty());
    }
    public boolean isFull(){
        return this.columns.stream().allMatch(column -> column.isFull())&& this.rows.stream().allMatch(row -> row.isFull());
    }
    public void playWhiteAt(int columnNumber){
            int rowNumber = columns.stream()
            .filter(column -> column.getColumnNumber() == columnNumber)
            .findFirst()
            .orElseThrow(() -> new RuntimeException(columnErrorMessage))
            .playWhiteAt();
            rows.get(rowNumber).playWhiteAt(columnNumber);
            anyoneWon();
    }
    public void playBlackAt(int column){
        int rowNumber = columns.stream()
        .filter(column1 -> column1.getColumnNumber() == column)
        .findFirst()
        .orElseThrow(() -> new RuntimeException(columnErrorMessage))
        .playBlackAt();
        rows.get(rowNumber).playBlackAt(column);
        anyoneWon();
    }
    public void anyoneWonVertical() {
        this.gameIsOver = columns.stream().anyMatch(Columns::winnerInColumn);
    }
    public void anyoneWonHorizontal() {
        this.gameIsOver = rows.stream().anyMatch(Rows::winnerInRow);
    }
    public void anyoneWonDiagonal() {
                this.gameIsOver = anyoneWonDiagonalChekcer(new WhitePiece()) || anyoneWonDiagonalChekcer(new BlackPiece());
    }
    public Piece getPieceAt(int column, int row) {
        return columns.get(column).getPieceAt(row);
    }
    public void anyoneWon() {
        Referee.getReferee(this.gameType).anyoneWon(this);
    }
    public boolean finished (){ 
        return this.gameIsOver;
    }
    public int getWidth() {
        return columns.size();
    }
    public int getHeight() {
        return rows.size();
    }
    public String getGameMode() {
        return gameType;
    }
    public int getAmountOfPieces (){
        return this.columns.stream().mapToInt(column -> column.getAmountOfPieces()).sum();
    }
    public String show(){
        return""; //TODO
    }
    public boolean anyoneWonDiagonalChekcer(Piece piece){
        
        boolean leftSlantDiagonal = IntStream.range(0, this.width - (4 - 1))
        .anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
            .anyMatch(row -> IntStream.range(0, 4)
                .allMatch(k -> this.getPieceAt(col + k, row + k).getClass() == piece.getClass())));

        boolean rightSlantDiagonal = IntStream.range(0, this.width - (4 - 1))
        .anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
            .anyMatch(row -> IntStream.range(0, 4)
                .allMatch(k -> this.getPieceAt(this.width - 1 - col - k, row + k).getClass() == piece.getClass())));
                return rightSlantDiagonal || leftSlantDiagonal;
    }
}
