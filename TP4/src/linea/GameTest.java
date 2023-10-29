package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Dashoboard dashoboard;
    @BeforeEach 
    public void setUp(){
        dashoboard = new Dashoboard(4,4,"A");

    }
    @Test public void testGameIntilaziesCorrectly(){
        assertGameStatus(4, 4, "A", false, false, true, 0);
    }
    @Test public void testGameCanitializeAnyWay(){
        dashoboard = new Dashoboard(5,5,"B");
        assertGameStatus(5, 5, "B", false, false, true, 0);
    }
    @Test public void testGameContinueAfterWhitePlay(){
        whitePlays(0);
        assertGameStatus(4, 4, "A", false, false, false, 1);
    }
    @Test public void testGameContinueAfterBlackPlay(){
        blackPlays(0);
        assertGameStatus(4, 4, "A", false, false, false, 1);
    }
    @Test public void testGameContinueAfterWhiteAndBlackPlay(){
        whitePlays(0);
        blackPlays(0);
        assertGameStatus(4, 4, "A", false, false, false, 2);
    }
    @Test public void testGameContinueAfterWhiteNormalAndBlackPlayInDifferentColumns(){
        whitePlays(0);
        blackPlays(1);
        assertGameStatus(4, 4, "A", false, false, false, 2);
    }
    @Test public void testGameContinueAfterWhitePlayInDifferentColumnsAndBlackNormal(){
        whitePlays(1);
        blackPlays(0);
        assertGameStatus(4, 4, "A", false, false, false, 2);
    }
    @Test public void testCantplayinAcolumnthatdontexist (){
        assertThrowsLike(()->whitePlays(4), getColumnErrorString());
    }
    @Test public void testCantplayinAcolumnthatdontexist2 (){
        assertThrowsLike(()->blackPlays(4), getColumnErrorString());
    }
    @Test public void testCantplayinAcolumnthatdontexist3 (){
        assertThrowsLike(()->whitePlays(-1), getColumnErrorString());
    }
    @Test public void testcanPlayMorethan2Times(){
        whitePlays(2,3,1);
        blackPlays(1,3,2);
        assertGameStatus(4, 4, "A", false, false, false, 6);
    }
    @Test public void testGameIsOverWhenWhiteWins(){
        whitePlays(0,0,0,0);
        assertTrue(dashoboard.finished());
    }
    @Test public void testGamecanwinAnyoneAnywhere(){
        whitePlays(0,0,0,3);
        blackPlays(1,1,1,1);
        assertTrue(dashoboard.finished());
    }
    @Test public void testGameCanEndHorizontally(){
        dashoboard = new Dashoboard(4, 4, "B");
        whitePlays(0,1,2,3);
        blackPlays(0,1,2);
        assertTrue(dashoboard.finished());
    }
    @Test public void testyounotalwaysWIn (){
        dashoboard = new Dashoboard(4, 4, "B");
        whitePlays(0,1,2);
        blackPlays(0,1,2);
        assertFalse(dashoboard.finished());
    }
    @Test public void testCantPlaceOverAfullcolumn(){
        whitePlays(0,0);
        blackPlays(0,0);
        assertThrowsLike(()->whitePlays(0), "No empty slots");
    }

    public String getColumnErrorString() {
        return dashoboard.columnErrorMessage;
    }
    private void assertGameStatus (int height, int width, String gameMode, boolean finished, boolean isFull, boolean isEmpty, int amountOfPieces){
        assertEquals(height, dashoboard.getHeight());
        assertEquals(width, dashoboard.getWidth());
        assertEquals(gameMode, dashoboard.getGameMode());
        assertEquals(finished, dashoboard.finished());
        assertEquals(isFull, dashoboard.isFull());
        assertEquals(isEmpty, dashoboard.isEmpty());
        assertEquals(amountOfPieces, dashoboard.getAmountOfPieces());
    }
    private void whitePlays(int ... columns){
        for (int column : columns) {
            dashoboard.playWhiteAt(column);
        }
    }
    private void blackPlays(int ... columns){
        for (int column : columns) {
            dashoboard.playBlackAt(column);
        }
    }
    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }

}
