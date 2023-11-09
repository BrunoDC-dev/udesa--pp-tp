package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Linea dashoboard;
    
    @BeforeEach 
    public void setUp() {
        dashoboard = new Linea(4, 4, 'A');
    }
    
    @Test public void testGameIntilaziesCorrectly() {
        assertGameStatus(4, 4, 'A', false, false, true, 0);
    }
    
    @Test public void testGameCanitializeAnyWay() {
        dashoboard = new Linea(5, 5, 'B');
        assertGameStatus(5, 5, 'B', false, false, true, 0);
    }
    
    @Test public void testGameContinueAfterWhitePlay() {
        dashoboard.playRedAt(0);
        assertGameStatus(4, 4, 'A', false, false, false, 1);
    }
    
    @Test public void testCannotPlayBlackfirst() {
        assertThrowsLike(() -> dashoboard.playBlueAt(0), getNotBlackTurnErrorString());
    }
    
    @Test public void testWhiteCannotPlayTwoTimes() {
        dashoboard.playRedAt(0);
        assertThrowsLike(() -> dashoboard.playRedAt(0), getNotWhiteTurnErrorString());
    }

    @Test public void testGameContinueAfterWhiteAndBlackPlay() {
        simulatePlaying(0, 0);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testGameContinueAfterWhiteNormalAndBlackPlayInDifferentColumns() {
        simulatePlaying(0, 1);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testGameContinueAfterWhitePlayInDifferentColumnsAndBlackNormal() {
        simulatePlaying(1, 0);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testCantplayinAcolumnthatdontexist() {
        assertThrowsLike(() -> simulatePlaying(4), getColumnErrorString());
    }
    
    @Test public void testCantplayinAcolumnthatdontexist2() {
        assertThrowsLike(() -> simulatePlaying(0, 4), getColumnErrorString());
    }
    
    @Test public void testCantplayinAcolumnthatdontexist3() {
        assertThrowsLike(() -> simulatePlaying(-1), getColumnErrorString());
    }
    
    @Test public void testcanPlayMorethan2Times() {
        simulatePlaying(2, 1, 3, 3, 1, 2);
        assertGameStatus(4, 4, 'A', false, false, false, 6);
    }
    
    @Test public void testGameIsOverWhenWhiteWins() {
        simulatePlaying(0, 1, 0, 1, 0, 1, 0);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testGamecanwinAnyoneAnywhere() {
        simulatePlaying(0, 1, 0, 1, 0, 1, 3, 1);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testGameCanEndHorizontally() {
        simulatePlaying(0, 0, 1, 1, 2, 2, 3);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testyounotalwaysWIn() {
        simulatePlaying(0, 0, 1, 1, 2, 2);
        asserGameOverSatus(false, false, false, false);
    }
    
    @Test
    public void testCantPlaceOverAfullcolumn() {
        assertThrowsLike(() -> simulatePlaying(0, 0, 0, 0, 0), getSlotErrorString());
    }
    
    @Test public void testCanWinDiagonal() {
        dashoboard = new Linea(4, 4, 'C');
        simulatePlaying(0, 1, 1, 2, 2, 3, 2, 3, 3, 0, 3);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testYouCanLooseDiagonal (){
        dashoboard = new Linea(4, 4, 'C');
        simulatePlaying(1, 0, 2, 2, 1, 2, 3, 3, 3, 3);
        asserGameOverSatus(false, false,false, false);
    }

    @Test public void testCanWinReverseDiagonal() {
        dashoboard = new Linea(4, 4, 'C');
        simulatePlaying(2, 0, 1, 3, 1, 2, 0, 1, 0, 0);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testCanDraw() {
        dashoboard = new Linea(1, 1, 'A');
        simulatePlaying(0);
        asserGameOverSatus(true, false, false, true);
    }

    @Test public void testCantPlay() {
        dashoboard = new Linea(1, 1, 'A');
        simulatePlaying(0);
        asserGameOverSatus(true, false, false, true);
        assertThrowsLike(() -> dashoboard.playRedAt(0), getCanNotPlayWhenGameIsOverErrorString());
        assertThrowsLike(() -> dashoboard.playBlueAt(0), getCanNotPlayWhenGameIsOverErrorString());
    }

    public String getColumnErrorString() {
        return Linea.columnErrorMessage;
    }

    public String getSlotErrorString() {
        return Linea.slotErrorMessage;
    }

    public String getNotBlackTurnErrorString() {
        return Linea.notBlackTurnErrorMessage;
    }

    public String getNotWhiteTurnErrorString() {
        return Linea.notWhiteTurnErrorMessage;
    }
    
    public String getCanNotPlayWhenGameIsOverErrorString() {
        return Linea.canNotPlayWhenGameIsOverErrorMessage;
    }
    
    private void assertGameStatus (int height, int width, char gameMode, boolean finished, boolean isFull, boolean isEmpty, int amountOfPieces){
        assertEquals(height, dashoboard.getHeight());
        assertEquals(width, dashoboard.getWidth());
        assertEquals(gameMode, dashoboard.getGameMode());
        assertEquals(finished, dashoboard.finished());
        assertEquals(isFull, dashoboard.isFull());
        assertEquals(isEmpty, dashoboard.isEmpty());
        assertEquals(amountOfPieces, dashoboard.getAmountOfPieces());
    }
    
    private void asserGameOverSatus(boolean finished, boolean hasWhiteWon, boolean hasBlackWon, boolean isAdraw) {
        assertEquals(finished, dashoboard.finished());
        assertEquals(hasWhiteWon, dashoboard.hasRedWon());
        assertEquals(hasBlackWon, dashoboard.hasBlueWon());
        assertEquals(isAdraw, dashoboard.isAdraw());
    }
    
    private void simulatePlaying(int... columns) {
        for (int i = 0; i < columns.length; i++) {
            if (i % 2 == 0) {
                dashoboard.playRedAt(columns[i]);
            } else {
                dashoboard.playBlueAt(columns[i]);
            }
        }
    }
    
    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }
}
