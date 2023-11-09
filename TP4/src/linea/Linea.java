package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {
    public static final String Red = "R ";
	public static final String Blue = "B ";
    public static String emptySlot = "  ";
	
	public static String positionIlegalErrorMessage = "Movement ilegal";
    public static String canNotPlayWhenGameIsOverErrorMessage = "Can not play when game is over";
	public static String notWhiteTurnErrorMessage = "Not w's turn";
	public static String notBlackTurnErrorMessage = "Not b's turn";
    public static String columnErrorMessage = "No such column";
    public static String slotErrorMessage = "No empty slots";

    private GameState state;
    private int width;
    private int height;
    private GameMode gameType;
    private ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
    private Printer printer = new Printer(this);
    
    public Linea(int width, int height, char gameType){
        this.width = width;
        this.height = height;
        this.gameType =  GameMode.getReferee(Character.toUpperCase(gameType));
        this.state= new PlayingRedSate(this);
        columns = IntStream.range(0, width)
        .mapToObj(column -> new ArrayList<String>()).
                        collect(Collectors.toCollection(ArrayList::new));
    }

    public void addPieceAt(int column, String piece) {
        if (columns.get(column-1).size() == height) {
            throw new RuntimeException(slotErrorMessage);
        }
        columns.get(column-1).add(piece);
    }

    public void checkItIsInBounds(int columnNumber) {
        if (columnNumber < 1 || columnNumber > this.width) {
            throw new RuntimeException(columnErrorMessage);
        }
    }

    public void playRedAt(int columnNumber) {
        checkItIsInBounds(columnNumber);
        state.playRedAt(columnNumber);
    }
    
    public void playBlueAt(int column) {
        checkItIsInBounds(column);
        state.playBlueAt(column);
    }
    
    public String getPieceAt(int column, int row) {
        return columns.get(column).stream()
                .skip(row)
                .findFirst()
                .map(String::valueOf)
                .orElse(emptySlot);
    }

    public boolean hasRedWon() {
        return gameType.anyoneWon(this, Red);
    }
    
    public boolean hasBlueWon() {
        return gameType.anyoneWon(this, Blue);
    }
    
    public boolean isAdraw() {
        return isFull() && !hasRedWon() && !hasBlueWon() ;
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
    
    public boolean anyoneWonDiagonal(String piece) {
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
    
            
    public String show() {
        return printer.show();
    }
    
    public void modifyState (GameState newState){
        state = newState;
    }
    
    public boolean finished() {
        return state.isFinished();
    }
    
    public boolean isPlayingRed() {
        return state.isPlayingRed();
    }
    
    public boolean isPlayingBlue() {
        return state.isPlayingBlue();
    }

    public GameState getState() {
        return this.state;
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

}
