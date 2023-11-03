package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.StringCache;

import linea.GameMode.Referee;

public class Dashoboard {
    public static final String White = "w";
	public static final String Black = "b";
	
	public static String positionIlegalErrorMessage = "Movement ilegal";
	public static String canNotPlayWhenGameIsOverErrorMessage = "Can not play when game is over";
	public static String notWhiteTurnErrorMessage = "Not w´s turn";
	public static String notBlackTurnErrorMessage = "Not b´s turn";
    public static String columnErrorMessage = "No such column";
    private GameState state;

    int width;
    int height;
    String gameType;
    ArrayList<Columns> columns = new ArrayList<Columns>();
    
   
    public Dashoboard(int width, int height, String gameType){
        this.width = width;
        this.height = height;
        this.gameType = gameType;
        this.state= new PlayingWhite(this);
        columns = IntStream.range(0, width).mapToObj(i -> new Columns(height, i)).collect(Collectors.toCollection(ArrayList::new));
    }
    public boolean isEmpty(){
        return this.columns.stream().allMatch(column -> column.isEmpty());
    }
    public boolean isFull(){
        return this.columns.stream().allMatch(column -> column.isFull());
    }
    public void addPieceAt(int column, String piece){
        columns.get(column).addPieceAt(piece);
    }
    public void playWhiteAt(int columnNumber){
            checkItIsInBounds(columnNumber);
            state.playWhiteAt(columnNumber);
    }
    
    public void playBlackAt(int column){
        checkItIsInBounds(column);
        state.playBlackAt(column);
    }
    public void modifyState (GameState newState){
        state = newState;
    }
    public void hasWon (String piece){
        Referee.getReferee(this.gameType).anyoneWon(this, piece);
    }
    public void anyoneWonVertical(String piece) {
        columns.stream()
               .filter(column -> column.winnerInColumn(piece))
               .findFirst()
               .ifPresent(column -> this.state = new GameOver(this));
    }
   public void anyoneWonHorizontal(String piece) {
    //iterate each column to check if there is 4 in a row
    for (int i = 0; i < height; i++) {
        int counter = 0;
        for (int j = 0; j < columns.size(); j++) {
            System.out.println(columns.get(j).getPieceAt(i) + "   " + piece);
            if (columns.get(j).getAmountOfPieces()>i && columns.get(j).getPieceAt(i).equals(piece)) {
                counter++;
            } else {
                counter = 0; // reset counter if the current piece is not equal to the given piece
            }
            if (counter == 4) {
                this.state = new GameOver(this);
                return;
            }
        }
    }
}
     public void anyoneWonDiagonal(String piece) {

                if (anyoneWonDiagonalChekcer(piece)) {
                    this.state = new GameOver(this);
                
                }
     }
    public String getPieceAt(int column, int row) {
        return columns.get(column).getPieceAt(row);
    }
    public boolean finished (){ 
        return this.state.isFinished();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
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
    public boolean anyoneWonDiagonalChekcer(String piece){
        
        boolean leftSlantDiagonal = IntStream.range(0, this.width - (4 - 1))
        .anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
            .anyMatch(row -> IntStream.range(0, 4)
                .allMatch(k -> this.getPieceAt(col + k, row + k) == piece)));

        boolean rightSlantDiagonal = IntStream.range(0, this.width - (4 - 1))
        .anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
            .anyMatch(row -> IntStream.range(0, 4)
                .allMatch(k -> this.getPieceAt(this.width - 1 - col - k, row + k) == piece)));
                return rightSlantDiagonal || leftSlantDiagonal;
    }

    public boolean isPlayingWhite() {
        return state.isPlayingWhite();
    }
    public boolean isPlayingBlack() {
        return state.isPlayingBlack();
    }
    public void checkItIsInBounds(int columnNumber) {
        if (columnNumber < 0 || columnNumber >= this.width) {
            throw new RuntimeException(columnErrorMessage);
        }
    } 

}
