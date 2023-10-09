package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class NemoTest {
    
    // @BeforeEach public void setUp() {
    //     Nemo nemo = new Nemo();
    // }
    
    @Test
    public void test01NemoStartsAtSurface() {
        Nemo nemo = new Nemo();
        assertEquals(0, nemo.getDepth());
    }

    @Test
    public void test02Descend() {
        Nemo nemo = new Nemo();
        nemo.descend();
        assertEquals(1, nemo.getDepth());
    }
    
    @Test
    public void test03AscendAfterDescending() {
        Nemo nemo = new Nemo();
        nemo.descend();
        nemo.ascend();
        assertEquals(0, nemo.getDepth());
    }

    @Test
    public void test04AscendigAtSurfaceDoesNothing() {
        Nemo nemo = new Nemo();
        nemo.ascend();
        assertEquals(0, nemo.getDepth());
    }
    
    @Test
    public void test05RotateLeft() {
        Nemo nemo = new Nemo();
        nemo.rotateLeft();
        assertEquals(90, nemo.getHeading());
    }
    
    @Test
    public void test06RotateRight() {
        Nemo nemo = new Nemo();
        nemo.rotateRight();
        assertEquals(270, nemo.getHeading());
    }

    @Test
    public void test07RotatingOpposingDirectionsDoesNothing() {
        Nemo nemo = new Nemo();
        nemo.rotateRight();
        nemo.rotateLeft();
        assertEquals(0, nemo.getHeading());
    }
    
    @Test
    public void testMoveForward() {
        Nemo nemo = new Nemo();
        nemo.moveForward();
        assertEquals(1, nemo.getPosition().getX());
    }
    
    @Test
    public void testLaunchCapsuleOnSurface() {
        Nemo nemo = new Nemo();
        nemo.launchCapsule();
        assertFalse(nemo.isCapsuleLaunched());
    }
    
    @Test
    public void testLaunchCapsuleAtDepth1() {
        Nemo nemo = new Nemo();
        nemo.descend();
        nemo.launchCapsule();
        assertFalse(nemo.isCapsuleLaunched());
    }
    
    @Test
    public void testLaunchCapsuleAtDepth2() {
        Nemo nemo = new Nemo();
        nemo.descend();
        nemo.descend();
        nemo.launchCapsule();
        assertTrue(nemo.isDestroyed());
    }
}
