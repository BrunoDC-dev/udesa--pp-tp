package linea;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {
    public static String Red = "\u001B[31m" + "()" + "\u001B[0m"; // 🔴 🔵 () >< ▶◀  ◀▶ ⛌⛊ 𝕏 
	public static String Blue = "\u001B[34m" + "><" + "\u001B[0m";
    public static String emptySlot = "--";
	
	public static String positionIlegalErrorMessage = "Movement ilegal";
    public static String canNotPlayWhenGameIsOverErrorMessage = "Can not play when game is over";
	public static String notRedsTurnErrorMessage = "Not Red's turn";
	public static String notBlueTurnErrorMessage = "Not blue's turn";
    public static String columnErrorMessage = "No such column";
    public static String slotErrorMessage = "No empty slots";
    public static String gameModeErrorMessage = "No such game mode";

    private GameState state;
    private int width;
    private int height;
    private GameMode gameMode;
    private ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
    private Printer printer = new Printer(this);
    
    public Linea(int width, int height, char gameMode){
        this.width = width;
        this.height = height;
        this.gameMode =  GameMode.getReferee(Character.toUpperCase(gameMode));
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

    public void playRedAt(int column) {
        checkItIsInBounds(column);
        state.playRedAt(column);
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
        return gameMode.anyoneWon(this, Red);
    }
    
    public boolean hasBlueWon() {
        return gameMode.anyoneWon(this, Blue);
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
    
    public boolean redWon() {
        return state.redWon();
    }
    
    public boolean blueWon() {
        return state.blueWon();
    }
    
    public boolean isAdraw() {
        return state.isADraw() ;
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
        return gameMode.show();
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
