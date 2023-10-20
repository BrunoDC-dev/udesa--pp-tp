package SubMarineProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SubMarineProject.Depths.ToDeep;
import SubMarineProject.Depths.Underwater;
import SubMarineProject.Directions.Direction;
import SubMarineProject.Directions.East;
import SubMarineProject.Directions.North;
import SubMarineProject.Directions.South;
import SubMarineProject.Directions.West;


public class NemoTest {

    @BeforeEach
    public void setUp() {
        nemo = new Nemo( 0 , 0);
    }
    @Test public void test00NemoCanBeCreatedAnyWhere() {
        nemo = new Nemo( 25 , 13);
        assertCoords(25, 13);
    }
    // Basic tests
    @Test public void test01NemoIsInSurface() {
        assertTrue(nemo.isInSurface());
    }
    @Test public void test02NemoStartsAtCenterByDefault() {
        assertCoords(0, 0);
    }
    @Test public void test03NemoStartsFacingEast(){
        assertEquals(new East(), nemo.getDirection());
    }
    @Test public void test04NemoStartsAtHeightZero(){
        assertEquals(0, nemo.getDepth());
    }
    @Test public void test05EmergeInSurface(){
        assertIsInSurfaceAfterCommands("u");
    }
    @Test public void test06NemoDescendsOneUnit(){
        nemo.receiveMessage("d");
        assertEquals(-1, nemo.getDepth());
    }
    @Test public void test07NotInSurfaceAfterDescending(){
        nemo.receiveMessage("d");
        assertFalse(nemo.isInSurface());
    }
    @Test public void test08DescendingDoesntAffectPosition(){
        assertCoordsAfterCommands(0, 0,  "d"  );
    }
    @Test public void test09DescendingDoesntAffectDirection(){
        assertDirection(new East(), "d");
    }
    @Test public void test10NemoDescendsAndEmerge(){
        nemo.receiveMessage("du");
        assertEquals(nemo.getDepth(), 0);
        assertTrue(nemo.isInSurface());
    }
    @Test public void test11NemoDescendsExtreme(){
        nemo.receiveMessage("ddddddddddddddddddd");
        assertEquals(nemo.getDepth(), -19);
        nemo.receiveMessage("uuuuuuuuuuuuuuuuuuu"); 
        assertEquals(nemo.getDepth(), 0);
        assertTrue(nemo.isInSurface());   
    }
    @Test public void test12NemoMoves90degreesToRight(){
        //Nemo gira 90 grados a la derecha
        assertDirection(new South(), "r");
    }
    @Test public void test13NemoMoves90degreesToLeft(){
        assertDirection(new North(), "l");
    }
    @Test public void test14NemoMoves180degreesToRight(){
        assertDirection(new West(), "rr");
    }
    @Test public void test15NemoMoves180degreesToLeft(){
        assertDirection(new West(), "ll");
    }
    @Test public void test16NemoMoves360degreesToRight(){
        assertDirection(new East(), "rrrr");
    }
    @Test public void test17NemoMoves360degreesToLeft(){
        assertDirection(new East(), "llll");
    }
    @Test public void test18RotatingLeftRightDoesNothing(){
        assertDirection(new East(), "rl");
    }
    @Test public void test18RotatingRightLeftDoesNothing(){
        assertDirection(new East(), "lr");
    }
    @Test public void test19NemoMovesInXCoordenate(){
        assertCoordsAfterCommands(1, 0, "f");
    }
    @Test public void test20NemoMovesInYCoordenate(){
        assertCoordsAfterCommands(0, 1, "lf");
    }
    @Test public void test21NemoMovesMinusXCoordenate(){
        assertCoordsAfterCommands(-1, 0, "llf");
    }
    @Test public void test22NemoMovesMinusYCoordenate(){
        assertCoordsAfterCommands(0, -1,"rf");
    }
    @Test public void test23NemoSpinAndMovesXCoordenate (){
        assertCoordsAfterCommands(1, 0, "rrrrf");
    }
    @Test public void test24NemoMovesXCoordinateTurnBack (){
        assertCoordsAfterCommands(0, 0,"frrf");
    }
    @Test public void test25NemoMovesYCoordinateTurnBack (){
        assertCoordsAfterCommands(0, 0, "lfrrf" );
    }
    @Test public void test26LiberatingCapsuleDoesntAffectDirection(){
        assertDirection(new East(), "m");
    }
    @Test public void test27LiberatingCapsuleDoesntAffectHeight(){
        assertIsInSurfaceAfterCommands("m");

    }
    @Test public void test28LiberatingCapsuleDoesntAffectPosition(){
        assertCoordsAfterCommands(0, 0, "m");
    }
    @Test public void test29LiberateMoreThanOneCapsuleDoesnAffect(){
        nemo.receiveMessage("mm");
        assertTrue(nemo.isInSurface());
        assertEquals(0, nemo.getDepth());
        assertEquals(new East(), nemo.getDirection());
        assertCoords(0, 0);
    }
    @Test public void test30CanLiberateCapsleInHeightMinus1(){
        nemo.receiveMessage("dm");
        assertEquals(nemo.getDepth(), -1);
        assertFalse(nemo.isInSurface());
    }
    @Test public void test31CanLiberateCapsulesInAnyPosition(){
        nemo.receiveMessage("fffffmrffffmdrffffm");
        assertEquals(nemo.getDepth(), -1);
        assertFalse(nemo.isInSurface());
        assertEquals(new East(), nemo.getDirection());
        assertCoords(1, -4);
    }
    @Test public void test20CantLiberateCapsleInHeightMinus2(){
        nemo.receiveMessage("dd");
        assertThrowsLike( () -> nemo.receiveMessage("m"), nemoExplodedString );
    }
    private Nemo nemo;
    private String nemoExplodedString = new ToDeep(new Underwater()).explosion_message;
    
    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( message, assertThrows( Exception.class, executable ).getMessage() );
    }

    private void assertDirection(Direction direction, String command) {
        nemo.receiveMessage(command);
        assertEquals(direction, nemo.getDirection());
    }

    private void assertCoordsAfterCommands( int x, int y, String  message ) {
        nemo.receiveMessage(message);
        assertCoords(x, y);
    }

    private void assertCoords( int x, int y ) {
        assertEquals(x, nemo.getCoordinates().getXcoord());
        assertEquals(y, nemo.getCoordinates().getYcoord());
    }
    private void assertIsInSurfaceAfterCommands( String message ) {
        nemo.receiveMessage(message);
        assertTrue(nemo.isInSurface());
        assertEquals(0, nemo.getDepth());
    }

}
