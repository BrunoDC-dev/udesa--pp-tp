package SubmarineProject;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NemoTest {
    private Nemo nemo;

    @BeforeEach
    public void setUp() {
        nemo = new Nemo();
    }

    @Test
    public void testProcessCommand_d() {
        nemo.processCommand('d');
        assertEquals(-1, nemo.getZ());
    }

    @Test
    public void testProcessCommand_u() {
        nemo.processCommand('u');
        assertEquals(1, nemo.getZ());
    }

    @Test
    public void testProcessCommand_l() {
        nemo.processCommand('l');
        assertEquals(-1, nemo.getX());
    }

    @Test
    public void testProcessCommand_r() {
        nemo.processCommand('r');
        assertEquals(1, nemo.getX());
    }

    @Test
    public void testProcessCommand_f() {
        nemo.processCommand('f');
        assertEquals(1, nemo.getY());
    }

    @Test
    public void testProcessCommand_m() {
        nemo.processCommand('m');
        assertFalse(nemo.isDestroyed());
    }

    @Test
    public void testProcessCommand_m_atDepthLimit() {
        nemo.setSurface(false);
        nemo.setCapsuleDepthLimit(0);
        nemo.processCommand('m');
        assertFalse(nemo.isDestroyed());
    }

    @Test
    public void testProcessCommand_m_atSurface() {
        nemo.setSurface(true);
        nemo.processCommand('m');
        assertFalse(nemo.isDestroyed());
    }

    @Test
    public void testProcessCommand_m_belowDepthLimit() {
        nemo.setSurface(false);
        nemo.setCapsuleDepthLimit(1);
        nemo.processCommand('d');
        nemo.processCommand('m');
        assertTrue(nemo.isDestroyed());
    }
}