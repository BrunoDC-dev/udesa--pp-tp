package SubMarineProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SubMarineProject.Direction.Direction;
import SubMarineProject.Direction.East;
import SubMarineProject.Direction.West;
import SubMarineProject.Direction.North;
import SubMarineProject.Direction.South;

import SubMarineProject.Height.BelowSurface;
import SubMarineProject.Height.ToDeep;


public class NemoTest {

    @BeforeEach
    public void setUp() {
        nemo = new Nemo( 0 , 0);
    }

    // Basic tests
    @Test public void test01NemoIsInSurface() {
        //Nemo incializa en la superfice
        assertTrue(nemo.isInSurface());
    }
    @Test public void test02NemoStartsAtCenterByDefault() {
        // Check that the X and Y coordinates are both 0
        assertCoords(0, 0);
    }
    @Test public void test03NemoStartsFacingEast(){
        //Nemo incializa con direccion 0
        assertEquals(new East(), nemo.getDirection());
    }
    @Test public void test00NemoStartsAtHeightZero(){
        //Nemo incializa en 0 de altura
        assertEquals(0, nemo.getHeight());
    }
    @Test public void test00EmergeInSurface(){
        //Nemo emerge en la superficie
        nemo.recieveMessage("u");
        assertTrue(nemo.isInSurface());
    }

    // Height tests
    @Test public void test00NemoDescendsOneUnit(){
        //Nemo desciende una unidad
        nemo.recieveMessage("d");
        assertEquals(-1, nemo.getHeight());
    }
    @Test public void test00NotInSurfaceAfterDescending(){
        //Nemo no esta en la superficie luego de descender
        nemo.recieveMessage("d");
        assertFalse(nemo.isInSurface());
    }
    @Test public void test00DescendingDoesntAffectPosition(){
        //Nemo desciende y no afecta su posicion
        assertCoordsAfterCommands(0, 0,  "d"  );
    }
    @Test public void test00DescendingDoesntAffectDirection(){
        //Nemo desciende y no afecta su direccion
        assertDirection(new East(), "d");
    }
    @Test public void test00NemoDescendsAndEmerge1(){
        //Nemo desciende y emerge a altura 0
        nemo.recieveMessage("du");
        assertEquals(nemo.getHeight(), 0);
    }
    @Test public void test00NemoDescendsAndEmerge2(){
        //Nemo desciende y emerge en la superficie
        nemo.recieveMessage("du");
        assertTrue(nemo.isInSurface());
    }
    // Agregar mas peor de emerge?
    
    // Direction tests
    @Test public void test00NemoMoves90degreesToRight(){
        //Nemo gira 90 grados a la derecha
        assertDirection(new South(), "r");
    }
    @Test public void test00NemoMoves90degreesToLeft(){
        //Nemo gira 90 grados a la izquierda
        assertDirection(new North(), "l");
    }
    @Test public void test00NemoMoves180degreesToRight(){
        //Nemo gira 180 grados a la derecha
        assertDirection(new West(), "rr");
    }
    @Test public void test00NemoMoves180degreesToLeft(){
        //Nemo gira 180 grados a la izquierda
        assertDirection(new West(), "ll");
    }
    @Test public void test00NemoMoves360degreesToRight(){
        //Nemo gira 360 grados a la derecha
        assertDirection(new East(), "rrrr");
    }
    @Test public void test00NemoMoves360degreesToLeft(){
        //Nemo gira 360 grados a la izquierda
        assertDirection(new East(), "llll");
    }
    @Test public void test00RotatingLeftRightDoesNothing(){
        assertDirection(new East(), "rl");
    }
    @Test public void test00RotatingRightLeftDoesNothing(){
        assertDirection(new East(), "lr");
    }

    // Movement tests
    @Test public void test00NemoMovesInXCoordenate(){
        //Nemo se mueve en el eje x
        assertCoordsAfterCommands(1, 0, "f");
    }
    @Test public void test00NemoMovesInYCoordenate(){
        //Nemo se mueve en el eje y
        assertCoordsAfterCommands(0, 1, "lf");
    }
    @Test public void test00NemoMovesMinusXCoordenate(){
        //Nemo se mueve en el eje x negativo
        assertCoordsAfterCommands(-1, 0, "llf");
    }
    @Test public void test00NemoMovesMinusYCoordenate(){
        //Nemo se mueve en el eje y negativo
        assertCoordsAfterCommands(0, -1,"rf");
    }
    @Test public void test00NemoSpinAndMovesXCoordenate (){
        //Nemo gira y se mueve en el eje x
        assertCoordsAfterCommands(1, 0, "rrrrf");
    }
    @Test public void test00NemoMovesXCoordinateTurnBack (){
        //Nemo se mueve en el eje x y gira 180 grados
        assertCoordsAfterCommands(0, 0,"frrf");
    }
    @Test public void test00NemoMovesYCoordinateTurnBack (){
        //Nemo se mueve en el eje y y gira 180 grados
        assertCoordsAfterCommands(0, 0, "lfrrf" );
    }

    // Capsule tests
    @Test public void test00LiberatingCapsuleDoesntAffectDirection(){
        //Nemo libera una capsula en la superficie
        nemo.recieveMessage("m");
        assertEquals(new East(), nemo.getDirection());
    }
    @Test public void test00LiberatingCapsuleDoesntAffectHeight(){
        //Nemo libera una capsula en la superficie
        nemo.recieveMessage("m");
        assertTrue(nemo.isInSurface());
    }
    @Test public void test00LiberatingCapsuleDoesntAffectPosition(){
        // Nemo libera una capsula en la superficie
        nemo.recieveMessage("m");
        assertCoordsAfterCommands(0, 0, nemoExplodedString);
    }
    @Test public void test00LiberateMoreThanOneCapsule(){
        //Nemo libera una capsula en la superficie
        nemo.recieveMessage("mm");
        assertTrue(nemo.isInSurface());
        assertEquals(new East(), nemo.getDirection());
        assertCoords(0, 0);
    }
    @Test public void test19CanLiberateCapsleInHeightMinus1(){
        //Nemo libera una capsula en la altura -1
        nemo.recieveMessage("dm");
        assertEquals(new East(), nemo.getDirection());
        assertEquals(nemo.getHeight(), -1);
        assertFalse(nemo.isInSurface());
    }
    @Test public void test20CantLiberateCapsleInHeightMinus2(){
        //Nemo libera una capsula en la altura -2
        nemo.recieveMessage("dd");
        assertThrowsLike( () -> nemo.recieveMessage("m"), nemoExplodedString );
    }

    private Nemo nemo;

    // private String nemoExplodedString = "Nemo exploded";
    private String nemoExplodedString = new ToDeep(new BelowSurface()).explosion_message;
    
    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( message, assertThrows( Exception.class, executable ).getMessage() );
    }

    private void assertDirection(Direction direction, String command) {
        nemo.recieveMessage(command);
        assertEquals(direction, nemo.getDirection());
    }

    private void assertCoordsAfterCommands( int x, int y, String  message ) {
        nemo.recieveMessage(message);
        assertCoords(x, y);
    }

    private void assertCoords( int x, int y ) {
        assertEquals(x, nemo.getCoordenadas().getXcoord());
        assertEquals(y, nemo.getCoordenadas().getYcoord());
    }

    // hacer dos para true/false?  /  pasarle argumento true/false y meter if?
    private void assertSurfaceAfterCommands( String message ) {
        nemo.recieveMessage(message);
        assertTrue(nemo.isInSurface());
    }
}
