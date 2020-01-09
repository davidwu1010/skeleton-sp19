import org.junit.Test;
import static org.junit.Assert.*;
public class TestUnionFind {
    @Test
    public void test() {
        UnionFind uf = new UnionFind(5);

        assertFalse(uf.connected(0, 1));
        uf.union(0, 1);
        assertTrue(uf.connected(0, 1));
        uf.union(1,4);
        assertTrue(uf.connected(0, 4));
        assertEquals(uf.sizeOf(0), 3);
        uf.union(2,3);
        uf.union(2, 4);
        assertTrue(uf.connected(0, 2));
        assertEquals(uf.sizeOf(0), 5);
    }
}
