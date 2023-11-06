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
        dashoboard = new Dashoboard(4,4,'A');

    }
    @Test public void testGameIntilaziesCorrectly(){
        assertGameStatus(4, 4, 'A', false, false, true, 0);
    }
    @Test public void testGameCanitializeAnyWay(){
        dashoboard = new Dashoboard(5,5,'B');
        assertGameStatus(5, 5, 'B', false, false, true, 0);
    }
    @Test public void testGameContinueAfterWhitePlay(){
        dashoboard.playWhiteAt(0);
        assertGameStatus(4, 4, 'A', false, false, false, 1);
    }
    @Test public void testCannotPlayBlackfirst(){
        assertThrowsLike(()->dashoboard.playBlackAt(0), Dashoboard.notBlackTurnErrorMessage);
    }
    @Test public void testGameContinueAfterWhiteAndBlackPlay(){
        simulatePlaying(0,0);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    @Test public void testGameContinueAfterWhiteNormalAndBlackPlayInDifferentColumns(){
        simulatePlaying(0,1);
         assertGameStatus(4, 4, 'A', false, false, false, 2);
     }
    @Test public void testGameContinueAfterWhitePlayInDifferentColumnsAndBlackNormal(){
        simulatePlaying(1,0);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
     @Test public void testCantplayinAcolumnthatdontexist (){
         assertThrowsLike(()->simulatePlaying(4), getColumnErrorString());
     }
    @Test public void testCantplayinAcolumnthatdontexist2 (){
         assertThrowsLike(()->simulatePlaying(0,4), getColumnErrorString());
    }
     @Test public void testCantplayinAcolumnthatdontexist3 (){
         assertThrowsLike(()->simulatePlaying(-1), getColumnErrorString());
     }
    @Test public void testcanPlayMorethan2Times(){
        simulatePlaying(2,1,3,3,1,2);
        assertGameStatus(4, 4, 'A', false, false, false, 6);
    }
    @Test public void testGameIsOverWhenWhiteWins(){
        simulatePlaying(0,1,0,1,0,1,0);
        assertTrue(dashoboard.finished());
        assertTrue(dashoboard.hasWhtieWon());
        assertFalse(dashoboard.hasBlackWon());
    }
    @Test public void testGamecanwinAnyoneAnywhere(){
        simulatePlaying(0,1,0,1,0,1,3,1);
        assertTrue(dashoboard.finished());
        assertFalse(dashoboard.hasWhtieWon());
        assertTrue(dashoboard.hasBlackWon());
     }
    @Test public void testGameCanEndHorizontally(){
         dashoboard = new Dashoboard(4, 4, 'B');
         simulatePlaying(0,0,1,1,2,2,3);
         assertTrue(dashoboard.finished());
         assertTrue(dashoboard.hasWhtieWon());
            assertFalse(dashoboard.hasBlackWon());
    }
    @Test public void testyounotalwaysWIn (){
        dashoboard = new Dashoboard(4, 4, 'B');
        simulatePlaying(0,0,1,1,2,2);
        assertFalse(dashoboard.finished());
        assertFalse(dashoboard.hasWhtieWon());
        assertFalse(dashoboard.hasBlackWon());
     }
    @Test public void testCantPlaceOverAfullcolumn(){
        
        assertThrowsLike(()->simulatePlaying(0,0,0,0,0), "No empty slots");
    }   
    @Test public void testCanWinDiagonal(){
        dashoboard = new Dashoboard(4, 4, 'C');
        simulatePlaying(0,1,1,2,2,3,2,3,3,0,3);
        assertTrue(dashoboard.finished());
        assertTrue(dashoboard.hasWhtieWon());
        assertFalse(dashoboard.hasBlackWon());
     }
    @Test public void testYouCanLooseDiagonal (){
        dashoboard = new Dashoboard(4, 4, 'C');
        simulatePlaying(1,0,2,2,1,2,3,3,3,3);
        assertFalse(dashoboard.finished());
        assertFalse(dashoboard.hasWhtieWon());
        assertFalse(dashoboard.hasBlackWon());
     }

     @Test
     public void testCanWinReverseDiagonal() {
         dashoboard = new Dashoboard(4, 4, 'C');
         simulatePlaying(2, 0, 1, 3, 1, 2, 0, 1, 0, 0);
         assertTrue(dashoboard.finished());
         assertFalse(dashoboard.hasWhtieWon());
         assertTrue(dashoboard.hasBlackWon());
     }
     // make it draw for the diferent types of games
    // @Test public void testCanDraw(){
    //     dashoboard = new Dashoboard(1, 1, 'A');
    //     //simulate a draw
    //     simulatePlaying(0);
    //     assertTrue(dashoboard.finished());
    //     assertFalse(dashoboard.hasWhtieWon());
    //     assertFalse(dashoboard.hasBlackWon());
    //     assertTrue(dashoboard.isAdraw());
    
    //  }
    public String getColumnErrorString() {
        return dashoboard.columnErrorMessage;
    }
    private void assertGameStatus (int height, int width, char gameMode, boolean finished, boolean isFull, boolean isEmpty, int amountOfPieces){
      //  System.out.println("Height:"+ height + " Width:" + width + " GameMode:" + gameMode + " Finished:" + finished + " IsFull:" + isFull + " IsEmpty:" + isEmpty + " AmountOfPieces:" + amountOfPieces);
        assertEquals(height, dashoboard.getHeight());
        assertEquals(width, dashoboard.getWidth());
        assertEquals(gameMode, dashoboard.getGameMode());
        assertEquals(finished, dashoboard.finished());
        assertEquals(isFull, dashoboard.isFull());
        assertEquals(isEmpty, dashoboard.isEmpty());
        assertEquals(amountOfPieces, dashoboard.getAmountOfPieces());
    }
    private void simulatePlaying (int ... columns){
       
         for (int i = 0; i < columns.length; i++){
              if (i % 2 == 0){
                dashoboard.playWhiteAt(columns[i]);
              }else{
                dashoboard.playBlackAt(columns[i]);
              }
         }
    }
    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }

}
