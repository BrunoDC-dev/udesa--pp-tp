package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import linea.GameMode.Referee;

public class Dashboard {
    public static final String White = "w ";
	public static final String Black = "b ";
	
	public static String positionIlegalErrorMessage = "Movement ilegal";
    public static String canNotPlayWhenGameIsOverErrorMessage = "Can not play when game is over";
	public static String notWhiteTurnErrorMessage = "Not w's turn";
	public static String notBlackTurnErrorMessage = "Not b's turn";
    public static String columnErrorMessage = "No such column";
    public static String slotErrorMessage = "No empty slots";
    public static String emptySlot = "  ";
    private GameState state;


    int width;
    int height;
    Referee gameType;
    ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
    Printer printer = new Printer(this);
    
    public Dashboard(int width, int height, char gameType){
        this.width = width;
        this.height = height;
        this.gameType =  Referee.getReferee(Character.toUpperCase(gameType));
        this.state= new PlayingWhite(this);
        columns = IntStream.range(0, width).mapToObj(column -> new ArrayList<String>()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void addPieceAt(int column, String piece) {
         if (columns.get(column).size() == height) {
            throw new RuntimeException(slotErrorMessage);
        }
        columns.get(column).add(piece);
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

    public String getPieceAt(int column, int row) {
        return columns.get(column).stream()
        .skip(row)
        .findFirst()
        .map(String::valueOf)
        .orElse(emptySlot);

    }
    public boolean finished (){ 
        return  hasWhiteWon() || hasBlackWon() || isAdraw();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public char getGameMode() {
        return gameType.getType();
    }

    public int getAmountOfPieces() {
        return this.columns.stream().mapToInt(column -> column.size()).sum();
    }

    public boolean isEmpty(){
        return this.getAmountOfPieces() == 0;
    }

    public boolean isFull(){
        return this.getAmountOfPieces() == this.width * this.height;
    }

    public String show() {
        return printer.show();
    }
    public boolean anyoneWonVertical(String piece) {
       return IntStream.range(0, this.width)
        .anyMatch(col -> IntStream.range(0, this.height - (4 - 1))
            .anyMatch(row -> IntStream.range(0, 4)
                .allMatch(k -> this.getPieceAt(col, row + k) == piece)));
    }
    public boolean anyoneWonHorizontal(String piece) {
        return IntStream.range(0, this.width - (4 - 1))
        .anyMatch(col -> IntStream.range(0, this.height)
            .anyMatch(row -> IntStream.range(0, 4)
                .allMatch(k -> this.getPieceAt(col + k, row) == piece)));
    }
    
    public boolean anyoneWonDiagonal(String piece){
        
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
    
    public boolean hasWon (String piece){
        return gameType.anyoneWon(this, piece);
    }
    public boolean hasWhiteWon (){
       return hasWon(White);
    }
    public boolean hasBlackWon() {
        return hasWon(Black);
    }

    public boolean isAdraw() {
        return this.isFull() && !this.hasWhiteWon() && !this.hasBlackWon() ;
    }

    public GameState getState() {
        return this.state;
    }
}
