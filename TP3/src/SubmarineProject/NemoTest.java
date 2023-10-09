package SubMarineProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class NemoTest {
 
    @Test public void test01NemoIsInSurface() {
        //Nemo incializa en la superfice
        Nemo nemo = new Nemo();
        assertTrue(nemo.isInSurface());
    }
    @Test public void test02NemoIsInCenter(){
        //Nemo incializa en el centro del eje cartesianos
        Nemo nemo = new Nemo();
        assertEquals((0,0), nemo.getCoordenates());
    }
    @Test public void test03NemoDirectionIs0(){
        //Nemo incializa con direccion 0
        Nemo nemo = new Nemo();
        assertEquals(0, nemo.getDirection());
    }
    @Test public void test04CapsuleLiberateInSurface(){
        //Nemo libera una capsula en la superficie
        Nemo nemo = new Nemo();
        nemo.recieveMessage("m");
        assertTrue(nemo.isInSurface());
        assertEquals(nemo.getAmounOfCapsules(), 1);
    }
    @Test public void test05LiberateMoreThanOneCapsule(){
        //Nemo libera una capsula en la superficie
        Nemo nemo = new Nemo();
        nemo.recieveMessage("m");
        nemo.recieveMessage("m");
        assertEquals(nemo.getAmounOfCapsules(), 2);
    }
    @Test public void test06EmergeInSurface(){
        //Nemo emerge en la superficie
        Nemo nemo = new Nemo();
        nemo.recieveMessage('u');
        assertTrue(nemo.isInSurface());
    }
    @Test public void test07NemoDescends(){
        //Nemo desciende en el eje z
        Nemo nemo = new Nemo();
        nemo.recieveMessage('d');
        assertFalse(nemo.isInSurface());
        assertEquals(nemo.getHeigth(), -1);
    }
    @Test public void test08NemoDescendsAndEmerge(){
        //Nemo desciende y emerge en la superficie
        Nemo nemo = new Nemo();
        nemo.recieveMessage('d');
        nemo.recieveMessage('u');
        assertTrue(nemo.isInSurface());
        assertEquals(nemo.getHeigth(), 0);
    }
    @Test public void test09NemoMoves90degreesToRight(){
        //Nemo gira 90 grados a la derecha
        Nemo nemo = new Nemo();
        nemo.recieveMessage('r');
        assertEquals(nemo.getDirection(), 270);
    }
    @Test public void test10NemoMoves90degreesToLeft(){
        //Nemo gira 90 grados a la izquierda
        Nemo nemo = new Nemo();
        nemo.recieveMessage('l');
        assertEquals(nemo.getDirection(), 90);
    }
    @Test public void test11NemoMovesDirectionEndIn0(){
        //Nemo gira 180 grados a la derecha
        Nemo nemo = new Nemo();
        nemo.recieveMessage('r');
        nemo.recieveMessage('l');
        assertEquals(nemo.getDirection(), 0);
    }
    @Test public void test12NemoMovesInXCoordenat(){
        //Nemo se mueve en el eje x
        Nemo nemo = new Nemo();
        nemo.recieveMessage('f');
        assertEquals(nemo.getCoordenates(), (1,0));
    }
    @Test public void test13NemoMovesInYCoordenat(){
        //Nemo se mueve en el eje y
        Nemo nemo = new Nemo();
        nemo.recieveMessage('l');
        nemo.recieveMessage('f');
        assertEquals(nemo.getCoordenates(), (0,1));
    }
    @Test public void test14NemoMovesMinusXCoordenate(){
        //Nemo se mueve en el eje x negativo
        Nemo nemo = new Nemo();
        nemo.recieveMessage('l');
        nemo.recieveMessage('l');
        nemo.recieveMessage('f');
        assertEquals(nemo.getCoordenates(), (-1,0));
    }
    @Test public void test15NemoMovesMinusYCoordenate(){
        //Nemo se mueve en el eje y negativo
        Nemo nemo = new Nemo();
        nemo.recieveMessage('r');
        nemo.recieveMessage('f');
        assertEquals(nemo.getCoordenates(), (0,-1));
    }
    @Test public void test16NemoSpinAndMovesXCoordenate (){
        //Nemo gira y se mueve en el eje x
        Nemo nemo = new Nemo();
        nemo.recieveMessage('r');
        nemo.recieveMessage('r');
        nemo.recieveMessage('r');
        nemo.recieveMessage('r');
        nemo.recieveMessage('f');
        assertEquals(nemo.getCoordenates(), (1,0));
    }
    @Test public void test17NemoMovesXCoordinateTurnBack (){
        //Nemo se mueve en el eje x y gira 180 grados
        Nemo nemo = new Nemo();
        nemo.recieveMessage('f');
        nemo.recieveMessage('r');
        nemo.recieveMessage('r');
        nemo.recieveMessage('f');
        assertEquals(nemo.getCoordenates(), (0,0));
    }
    @Test public void test18NemoMovesYCoordinateTurnBack (){
        //Nemo se mueve en el eje y y gira 180 grados
        Nemo nemo = new Nemo();
        nemo.recieveMessage('l');
        nemo.recieveMessage('f');
        nemo.recieveMessage('r');
        nemo.recieveMessage('r');
        nemo.recieveMessage('f');
        assertEquals(nemo.getCoordenates(), (0,0));
    }
    @Test public void test19CanLiberateCapsleInHeightMinus1(){
        //Nemo libera una capsula en la altura -1
        Nemo nemo = new Nemo();
        nemo.recieveMessage('d');
        nemo.recieveMessage('m');
        assertEquals(nemo.getAmounOfCapsules(), 1);
        assertEquals(nemo.getHeigth(), -1);
        assertFalse(nemo.isInSurface());
    }
    @Test public void test20CantLiberateCapsleInHeightMinus2(){
        //Nemo libera una capsula en la altura -2
        Nemo nemo = new Nemo();
        nemo.recieveMessage('d');
        nemo.recieveMessage('d');
        assertThrowsLike(()->nemo.recieveMessage('m'), nemoExploedString);
    }
    private String nemoExploedString = "Nemo exploded";
    private void assertThrowsLike( Executable executable, String message ) {
    
        assertEquals( message,assertThrows( Exception.class, executable ).getMessage() );
      }
}
