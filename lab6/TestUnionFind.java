import org.junit.Test;
import static org.junit.Assert.*;
public class TestUnionFind {
    @Test
    public void test() {
        UnionFind uf = new UnionFind(5);

        assertFalse(uf.connected(0, 1));
        uf.union(0, 1);
        assertEquals(4, uf.size);
        assertTrue(uf.connected(0, 1));
        uf.union(1,4);
        assertEquals(3, uf.size);
        assertTrue(uf.connected(0, 4));
        assertEquals(uf.sizeOf(0), 3);
        uf.union(2,3);
        assertEquals(2, uf.size);
        uf.union(2, 4);
        assertEquals(1, uf.size);
        assertTrue(uf.connected(0, 2));
        assertEquals(uf.sizeOf(0), 5);
    }
}
