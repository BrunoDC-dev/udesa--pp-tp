package SubMarineProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SubMarineProject.Brujula.Brujula;
import SubMarineProject.Brujula.East;
import SubMarineProject.Brujula.North;
import SubMarineProject.Brujula.South;



public class NemoTest {

    @BeforeEach
    public void setUp() {
        nemo = new Nemo();
    }
 
    @Test public void test01NemoIsInSurface() {
        //Nemo incializa en la superfice
        assertTrue(nemo.isInSurface());
    }
    @Test public void test02NemoIsInCenter() {
        // Check that the X and Y coordinates are both 0
        assertCoords(0, 0);
        
    }
    @Test public void test03NemoDirectionIs0(){
        //Nemo incializa con direccion 0
        assertEquals(new East(), nemo.getDirection());
    }
    @Test public void test04CapsuleLiberateInSurface(){
        //Nemo libera una capsula en la superficie
        nemo.recieveMessage("m");
        assertTrue(nemo.isInSurface());
        assertEquals(nemo.getAmountOfCapsules(), 1);
    }
    @Test public void test05LiberateMoreThanOneCapsule(){
        //Nemo libera una capsula en la superficie
        nemo.recieveMessage("mm");
        assertEquals(nemo.getAmountOfCapsules(), 2);
    }
    @Test public void test06EmergeInSurface(){
        //Nemo emerge en la superficie
        nemo.recieveMessage("u");
        assertTrue(nemo.isInSurface());
    }
    @Test public void test07NemoDescends(){
        //Nemo desciende en el eje z
        nemo.recieveMessage("d");
        assertFalse(nemo.isInSurface());
        assertEquals(nemo.getHeight(), -1);
    }
    @Test public void test08NemoDescendsAndEmerge(){
        //Nemo desciende y emerge en la superficie
        nemo.recieveMessage("du");
        assertTrue(nemo.isInSurface());
        assertEquals(nemo.getHeight(), 0);
    }
     
    @Test public void test09NemoMoves90degreesToRight(){
        //Nemo gira 90 grados a la derecha
        assertDirection(new South(), "r");
    }
    @Test public void test10NemoMoves90degreesToLeft(){
        //Nemo gira 90 grados a la izquierda
        assertDirection(new North(), "l");
    }  
    @Test public void test11NemoMovesDirectionEndIn0(){
        //Nemo gira 180 grados a la derecha
        assertDirection(new East(), "rl");
    }
    @Test public void test12NemoMovesInXCoordenat(){
        //Nemo se mueve en el eje x
        assertCoordsAfterCommands(1, 0, "f");
    }
    @Test public void test13NemoMovesInYCoordenat(){
        //Nemo se mueve en el eje y
        assertCoordsAfterCommands(0, 1, "lf");
    }
    @Test public void test14NemoMovesMinusXCoordenate(){
        //Nemo se mueve en el eje x negativo

        assertCoordsAfterCommands(-1, 0, "llf");
    }
    @Test public void test15NemoMovesMinusYCoordenate(){
        //Nemo se mueve en el eje y negativo
        assertCoordsAfterCommands(0, -1,"rf");
    }
    @Test public void test16NemoSpinAndMovesXCoordenate (){
        //Nemo gira y se mueve en el eje x
        assertCoordsAfterCommands(1, 0, "rrrrf");
    }
    @Test public void test17NemoMovesXCoordinateTurnBack (){
        //Nemo se mueve en el eje x y gira 180 grados
        assertCoordsAfterCommands(0, 0,"frrf");
    }
    @Test public void test18NemoMovesYCoordinateTurnBack (){
        //Nemo se mueve en el eje y y gira 180 grados
        assertCoordsAfterCommands(0, 0,  "lfrrf"  );
    }
    @Test public void test19CanLiberateCapsleInHeightMinus1(){
        //Nemo libera una capsula en la altura -1
        nemo.recieveMessage("dm");
        assertEquals(nemo.getAmountOfCapsules(), 1);
        assertEquals(nemo.getHeight(), -1);
        assertFalse(nemo.isInSurface());
    }
    @Test public void test20CantLiberateCapsleInHeightMinus2(){
        //Nemo libera una capsula en la altura -2
        nemo.recieveMessage("dd");
        assertThrowsLike(()->nemo.recieveMessage("m"), nemoExploedString);
    }

    private Nemo nemo;

    private String nemoExploedString = "Nemo exploded";
    
    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( message,assertThrows( Exception.class, executable ).getMessage() );
    }

    private void assertDirection(Brujula direction, String command) {
        nemo.recieveMessage(command);
        assertEquals(direction, nemo.getDirection());
    }

    private void assertCoordsAfterCommands( int x, int y, String  message ) {
        nemo.recieveMessage(message);
        assertCoords(x, y);
    }
    

    private void assertCoords( int x, int y ) {
        assertEquals(x, nemo.getXcoord());
        assertEquals(y, nemo.getYcoord());
    }
}
