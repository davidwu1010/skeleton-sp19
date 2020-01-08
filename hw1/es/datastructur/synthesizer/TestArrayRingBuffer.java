package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test(expected = RuntimeException.class)
    public void testOverflow() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
    }

    @Test(expected = RuntimeException.class)
    public void testUnderflow1() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.dequeue();
    }

    @Test(expected = RuntimeException.class)
    public void testUnderflow2() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.peek();
    }

    @Test
    public void generalTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertEquals(4, arb.capacity());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        assertEquals(4, arb.fillCount());
        assertEquals(1, arb.peek());
        assertEquals(1, arb.dequeue());
        arb.enqueue(5);
        assertEquals(2, arb.dequeue());
        assertEquals(3, arb.dequeue());
        assertEquals(4, arb.dequeue());
        assertEquals(5, arb.dequeue());
        arb.enqueue(6);
        assertEquals(6, arb.dequeue());
    }
}
