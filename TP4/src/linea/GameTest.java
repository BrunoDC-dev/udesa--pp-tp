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
        dashoboard.playRedAt(1);
        assertGameStatus(4, 4, 'A', false, false, false, 1);
    }
    
    @Test public void testCannotPlayBlackfirst() {
        assertThrowsLike(() -> dashoboard.playBlueAt(1), getNotBlackTurnErrorString());
    }
    
    @Test public void testWhiteCannotPlayTwoTimes() {
        dashoboard.playRedAt(1);
        assertThrowsLike(() -> dashoboard.playRedAt(1), getNotWhiteTurnErrorString());
    }

    @Test public void testGameContinueAfterWhiteAndBlackPlay() {
        simulatePlaying(1, 1);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testGameContinueAfterWhiteNormalAndBlackPlayInDifferentColumns() {
        simulatePlaying(1, 2);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testGameContinueAfterWhitePlayInDifferentColumnsAndBlackNormal() {
        simulatePlaying(2, 1);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testCantplayinAcolumnthatdontexist() {
        assertThrowsLike(() -> simulatePlaying(5), getColumnErrorString());
    }
    
    @Test public void testCantplayinAcolumnthatdontexist2() {
        assertThrowsLike(() -> simulatePlaying(1, 5), getColumnErrorString());
    }
    
    @Test public void testCantplayinAcolumnthatdontexist3() {
        assertThrowsLike(() -> simulatePlaying(0), getColumnErrorString());
    }
    
    @Test public void testcanPlayMorethan2Times() {
        simulatePlaying(3, 2, 4, 4, 2, 3);
        assertGameStatus(4, 4, 'A', false, false, false, 6);
    }
    
    @Test public void testGameIsOverWhenWhiteWins() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 1);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testGamecanwinAnyoneAnywhere() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 4, 2);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testGameCanEndHorizontally() {
        simulatePlaying(1, 1, 2, 2, 3, 3, 4);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testyounotalwaysWIn() {
        simulatePlaying(1, 1, 2, 2, 3, 3);
        asserGameOverSatus(false, false, false, false);
    }
    
    @Test
    public void testCantPlaceOverAfullcolumn() {
        assertThrowsLike(() -> simulatePlaying(1, 1, 1, 1, 1), getSlotErrorString());
    }
    
    @Test public void testCanWinDiagonal() {
        dashoboard = new Linea(4, 4, 'C');
        simulatePlaying(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testYouCanLooseDiagonal (){
        dashoboard = new Linea(4, 4, 'C');
        simulatePlaying(2, 1, 3, 3, 2, 3, 4, 4, 4, 4);
        asserGameOverSatus(false, false,false, false);
    }

    @Test public void testCanWinReverseDiagonal() {
        dashoboard = new Linea(4, 4, 'C');
        simulatePlaying(3, 1, 2, 4, 2, 3, 1, 2, 1, 1);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testCanDraw() {
        dashoboard = new Linea(1, 1, 'A');
        simulatePlaying(1);
        asserGameOverSatus(true, false, false, true);
    }

    @Test public void testCantPlay() {
        dashoboard = new Linea(1, 1, 'A');
        simulatePlaying(1);
        asserGameOverSatus(true, false, false, true);
        assertThrowsLike(() -> dashoboard.playRedAt(1), getCanNotPlayWhenGameIsOverErrorString());
        assertThrowsLike(() -> dashoboard.playBlueAt(1), getCanNotPlayWhenGameIsOverErrorString());
    }
    
    @Test public void testPrinter() {
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│  │  │  │  │" + "\n" +
                            "│  │  │  │  │" + "\n" +
                            "│  │  │  │  │" + "\n" +
                            "│  │  │  │  │" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "< Playing Red >" + "\n";
        assertEquals(printGame, dashoboard.show());
    }
    
    @Test public void testPrinterAfterPlaying() {
        simulatePlaying(1);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│  │  │  │  │" + "\n" +
                            "│  │  │  │  │" + "\n" +
                            "│  │  │  │  │" + "\n" +
                            "│R │  │  │  │" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "< Playing Blue >" + "\n";
        assertEquals(printGame, dashoboard.show());
    }
    
    @Test public void testPrinterAfterWinnigRED() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 1);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│R │  │  │  │" + "\n" +
                            "│R │B │  │  │" + "\n" +
                            "│R │B │  │  │" + "\n" +
                            "│R │B │  │  │" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "<  Red wins   >" + "\n";
        assertEquals(printGame, dashoboard.show());
    }
    @Test public void testPrinterAfterWinnigBlue() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 4, 2);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│  │B │  │  │" + "\n" +
                            "│R │B │  │  │" + "\n" +
                            "│R │B │  │  │" + "\n" +
                            "│R │B │  │R │" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "<  Blue wins   >" + "\n";
        assertEquals(printGame, dashoboard.show());
    }

    @Test public void testPrinterDraw() {
        simulatePlaying(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 2, 1, 4, 3);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│B │R │B │R │" + "\n" +
                            "│R │B │R │B │" + "\n" +
                            "│R │B │R │B │" + "\n" +
                            "│R │B │R │B │" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "<Game is a draw >" + "\n";
        assertEquals(printGame, dashoboard.show());
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
