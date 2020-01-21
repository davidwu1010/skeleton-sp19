package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTest {
    @Test
    public void testPercolation() {
        Percolation p = new Percolation(1);
        p.open(0, 0);
        assertTrue(p.percolates());

        p = new Percolation(2);
        p.open(0, 0);
        p.open(1, 1);
        assertFalse(p.percolates());
        p.open(0, 1);
        assertTrue(p.percolates());

        p = new Percolation(3);
        p.open(2, 2);
        p.open(1,1);
        p.open(0, 0);
        assertFalse(p.percolates());
        p.open(0, 1);
        p.open(2, 1);
        assertTrue(p.percolates());
    }
}
