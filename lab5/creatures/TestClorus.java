package creatures;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals("clorus", c.name());
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
        c.attack(new Plip(1));
        assertEquals(2.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(3);
        Clorus offspring = c.replicate();
        assertEquals(1.5, c.energy(), 0.01);
        assertEquals(1.5, offspring.energy(), 0.01);
        assertNotSame(c, offspring);
    }

}
