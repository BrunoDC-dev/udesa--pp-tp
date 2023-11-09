package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {
    public static final String White = "w ";
	public static final String Black = "b ";
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
        this.state= new PlayingWhiteSate(this);
        columns = IntStream.range(0, width)
        .mapToObj(column -> new ArrayList<String>()).
                        collect(Collectors.toCollection(ArrayList::new));
    }

    public void addPieceAt(int column, String piece) {
        if (columns.get(column).size() == height) {
            throw new RuntimeException(slotErrorMessage);
        }
        columns.get(column).add(piece);
    }

    public void checkItIsInBounds(int columnNumber) {
        if (columnNumber < 0 || columnNumber >= this.width) {
            throw new RuntimeException(columnErrorMessage);
        }
    }

    public void playWhiteAt(int columnNumber) {
        checkItIsInBounds(columnNumber);
        state.playWhiteAt(columnNumber);
    }
    
    public void playBlackAt(int column) {
        checkItIsInBounds(column);
        state.playBlackAt(column);
    }
    
    public String getPieceAt(int column, int row) {
        return columns.get(column).stream()
                .skip(row)
                .findFirst()
                .map(String::valueOf)
                .orElse(emptySlot);
    }

    public boolean hasWhiteWon() {
        return gameType.anyoneWon(this, White);
    }
    
    public boolean hasBlackWon() {
        return gameType.anyoneWon(this, Black);
    }
    
    public boolean isAdraw() {
        return isFull() && !hasWhiteWon() && !hasBlackWon() ;
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
    
    public boolean isPlayingWhite() {
        return state.isPlayingWhite();
    }
    
    public boolean isPlayingBlack() {
        return state.isPlayingBlack();
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
