package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;

public class GameTest {

    private Linea game;
    private String R;
    private String B;
    
    @BeforeEach 
    public void setUp() {
        game = new Linea(4, 4, 'A');
        R = getRedPiece();
        B = getBluePiece();
    }
    
    @Test public void testGameInitializesCorrectly() {
        assertGameStatus(4, 4, 'A', false, false, true, 0);
    }
    
    @Test public void testGameInitializesWithAnySizeAndGameMode () {
        game = new Linea(5, 5, 'B');
        assertGameStatus(5, 5, 'B', false, false, true, 0);
    }
    
    @Test public void testGameContinuesAfterRedPlay() {
        game.playRedAt(1);
        assertGameStatus(4, 4, 'A', false, false, false, 1);
    }
    
    @Test public void testCannotPlayBlueFirst() {
        assertThrowsLike(() -> game.playBlueAt(1), getNotBlueTurnErrorString());
    }
    
    @Test public void testRedCannotPlayTwoTimes() {
        game.playRedAt(1);
        assertThrowsLike(() -> game.playRedAt(1), getNotRedsTurnErrorString());
    }

    @Test public void testGameContinuesAfterRedAndBluekPlay() {
        simulatePlaying(1, 1);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testGameContinueAfterRedNormalAndBluePlayInDifferentColumns() {
        simulatePlaying(1, 2);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testGameContinueAfterRedPlayInDifferentColumnsAndBluekNormal() {
        simulatePlaying(2, 1);
        assertGameStatus(4, 4, 'A', false, false, false, 2);
    }
    
    @Test public void testCantPlayInColumnThatDontExist() {
        assertThrowsLike(() -> simulatePlaying(5), getColumnErrorString());
    }
    
    @Test public void testCantPlayInColumnThatDontExist2() {
        assertThrowsLike(() -> simulatePlaying(1, 5), getColumnErrorString());
    }
    
    @Test public void testCantPlayInColumnThatDontExist3() {
        assertThrowsLike(() -> simulatePlaying(0), getColumnErrorString());
    }
    @Test public void testCantPlayGameWithWrongGameMode() {
        assertThrowsLike(() ->new Linea(4,4,'z'), getGameModeErrorMessage());
    }
    
    @Test public void testRedCanWinVertical() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 1);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testBlueCanWinVertical() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 4, 2);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testRedCanWinHorizontally() {
        simulatePlaying(1, 1, 2, 2, 3, 3, 4);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testBlueCanWinHorizontally() {
        simulatePlaying(1, 1, 2, 2, 3, 3, 1, 4, 1, 4);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testCantWinDiagonalInDefaultGameMode() {
        simulatePlaying(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
        asserGameOverSatus(false, false, false, false);
    }
    
    @Test public void testCantPlayAfterRedWon() {
        simulatePlaying(1, 1, 2, 2, 3, 3, 4);
        assertNoOneCanPalyAfterWinnig();
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testCantPlayAfterBlueWon() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 4, 2);
        assertNoOneCanPalyAfterWinnig();
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testCantDrawInDefaultGameMode() {
        simulatePlaying(1,1,1,1,2,3,2,3,2,3,3,2,4,4,4,4);
        assertGameDrawStatus();
    }

    @Test public void testCantPlaceOverAfullcolumn() {
        assertThrowsLike(() -> simulatePlaying(1, 1, 1, 1, 1), getSlotErrorString());
    }
    
    @Test public void testRedWinDiagonalGameModeB() {
        game = new Linea(4, 4, 'B');
        simulatePlaying(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testBlueWinDiagonalGameModeB() {
        game = new Linea(4, 4, 'B');
        simulatePlaying(2,4,1,1,3,3,2,2,1,1);
        asserGameOverSatus(true, false, true, false);
    }

    @Test public void testYouCantWinHorizontallyInGameModeB() {
        game = new Linea(4, 4, 'B');
        simulatePlaying(1, 1, 2, 2, 3, 3, 4, 4);
        asserGameOverSatus(false, false, false, false);
    }
    
    @Test public void testYouCantWinVerticallyInGameModeB() {
        game = new Linea(4, 4, 'B');
        simulatePlaying(1, 2, 1, 2, 1, 2, 1, 2);
        asserGameOverSatus(false, false, false, false);
    }
    
    @Test public void testCantPlayAfterWinnigGameModeB() {
        game = new linea.Linea(4, 4, 'B');
        simulatePlaying(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
        assertNoOneCanPalyAfterWinnig();
    }
    
    @Test public void testCantDrawInGameModeB() {
        game = new Linea(4, 4, 'B');
        simulatePlaying(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4);
        assertGameDrawStatus();
    }

    @Test public void testCanWinVericallyGameModeC() {
        game = new Linea(4, 4, 'C');
        simulatePlaying(1, 2, 1, 2, 1, 2, 1);
        asserGameOverSatus(true, true, false, false);
    }
    
    @Test public void testCanWinHorizontallyGameModeC() {
        game = new Linea(4, 4, 'C');
        simulatePlaying(1, 1, 2, 2, 3, 3, 1, 4, 2, 4);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testCanWinDiagonalGameModeC() {
        game = new Linea(4, 4, 'C');
        simulatePlaying(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
        asserGameOverSatus(true, true, false, false);
    }

    @Test public void testCanWinInversedDiagonalGameModeC() {
        game = new Linea(4, 4, 'C');
        simulatePlaying(2, 4, 1, 1, 3, 3, 2, 2, 1, 1);
        asserGameOverSatus(true, false, true, false);
    }
    
    @Test public void testCanDrawInGameModeC() {
        game = new Linea(4, 4, 'C');
        simulatePlaying(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 2, 1, 4, 3);
        assertGameDrawStatus();
    }
    
    @Test public void testPrinter() {
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "GameMode: A" + "\n"+
                            "< Playing Red >" + "\n";
        assertEquals(printGame, game.show());
    }
    
    @Test public void testPrinterAfterPlaying() {
        simulatePlaying(1);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                            "│--│--│--│--│" + "\n" +
                        "│"+R +"│--│--│--│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "GameMode: A" + "\n"+
                            "< Playing Blue >" + "\n";
        assertEquals(printGame, game.show());
    }
    
    @Test public void testPrinterAfterWinnigRED() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 1);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│"+R+"│--│--│--│" + "\n" +
                            "│"+R+"│"+B+"│--│--│" + "\n" +
                            "│"+R+"│"+B+"│--│--│" + "\n" +
                            "│"+R+"│"+B+"│--│--│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "GameMode: A" + "\n"+
                            "<  Red wins   >" + "\n";
        assertEquals(printGame, game.show());
    }
    @Test public void testPrinterAfterWinnigBlue() {
        simulatePlaying(1, 2, 1, 2, 1, 2, 4, 2);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│--│"+B+"│--│--│" + "\n" +
                            "│"+R+"│"+B+"│--│--│" + "\n" +
                            "│"+R+"│"+B+"│--│--│" + "\n" +
                            "│"+R+"│"+B+"│--│"+R+"│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "GameMode: A" + "\n"+
                            "<  Blue wins   >" + "\n";
        assertEquals(printGame, game.show());
    }

    @Test public void testPrinterDraw() {
        simulatePlaying(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 2, 1, 4, 3);
        String printGame =  "┌──┬──┬──┬──┐" + "\n" +
                            "│"+B+"│"+R+"│"+B+"│"+R+"│" + "\n" +
                            "│"+R+"│"+B+"│"+R+"│"+B+"│" + "\n" +
                            "│"+R+"│"+B+"│"+R+"│"+B+"│" + "\n" +
                            "│"+R+"│"+B+"│"+R+"│"+B+"│" + "\n" +
                            "├──┼──┼──┼──┤" + "\n" +
                            "│1 │2 │3 │4 │" + "\n" +
                            "└──┴──┴──┴──┘" + "\n" +
                            "GameMode: A" + "\n"+
                            "<Game is a draw >" + "\n";
        assertEquals(printGame, game.show());
    }
    
    public String getRedPiece() {
        return Linea.Red;
    }

    public String getBluePiece() {
        return Linea.Blue;
    }

    public String getGameModeErrorMessage() {
        return Linea.gameModeErrorMessage;
    }

    public String getColumnErrorString() {
        return Linea.columnErrorMessage;
    }

    public String getSlotErrorString() {
        return Linea.slotErrorMessage;
    }

    public String getNotBlueTurnErrorString() {
        return Linea.notBlueTurnErrorMessage;
    }

    public String getNotRedsTurnErrorString() {
        return Linea.notRedsTurnErrorMessage;
    }
    
    public String getCanNotPlayWhenGameIsOverErrorString() {
        return Linea.canNotPlayWhenGameIsOverErrorMessage;
    }
    
    private void assertGameStatus (int height, int width, char gameMode, boolean finished, boolean isFull, boolean isEmpty, int amountOfPieces){
        assertEquals(height, game.getHeight());
        assertEquals(width, game.getWidth());
        assertEquals(gameMode, game.getGameMode());
        assertEquals(finished, game.finished());
        assertEquals(isFull, game.isFull());
        assertEquals(isEmpty, game.isEmpty());
        assertEquals(amountOfPieces, game.getAmountOfPieces());
    }
    
    private void asserGameOverSatus(boolean finished, boolean hasRedWon, boolean hasBlueWon, boolean isAdraw) {
        System.out.println(game.finished());
        System.out.println(game.redWon());
        System.out.println(game.blueWon());
        System.out.println(game.isAdraw());
        assertEquals(finished, game.finished());
        assertEquals(hasRedWon, game.redWon());
        assertEquals(hasBlueWon, game.blueWon());
        assertEquals(isAdraw, game.isAdraw());
    }
    
    private void assertGameDrawStatus() {
        asserGameOverSatus(true, false, false, true);
        assertNoOneCanPalyAfterWinnig();
    }

    private void assertNoOneCanPalyAfterWinnig() {
        assertThrowsLike(() -> game.playRedAt(1), getCanNotPlayWhenGameIsOverErrorString());
        assertThrowsLike(() -> game.playBlueAt(1), getCanNotPlayWhenGameIsOverErrorString());
    }  

    private void simulatePlaying(int... columns) {
        IntStream.range(0, columns.length)
            .forEach(i -> {
                if (i % 2 == 0) {
                    game.playRedAt(columns[i]);
                } else {
                    game.playBlueAt(columns[i]);
                }
            });
    }
    
    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }
}
