package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void basicTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("mate", 0.3);
        pq.add("dude", 0.1);
        pq.add("pal", 0.6);
        pq.add("lad", 0.8);
        assertEquals(4, pq.size());
        assertEquals("dude", pq.removeSmallest());
        assertEquals("mate", pq.removeSmallest());
        assertEquals("pal", pq.removeSmallest());
        assertEquals("lad", pq.removeSmallest());
        assertEquals(0, pq.size());
    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("guava", 2);
        pq.add("melon", 2.2);
        pq.add("cherry", 2.3);
        assertEquals("guava", pq.getSmallest());
        pq.changePriority("guava", 4);
        assertEquals("melon", pq.removeSmallest());
        assertEquals("cherry", pq.removeSmallest());
    }
}
