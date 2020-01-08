package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public

public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[])new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount += 1;
        rb[last] = x;
        last = (last + 1) % rb.length;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount -= 1;
        T item = rb[first];
        first = (first + 1) % rb.length;
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T>{
        private int pos = first;
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[pos];
            pos = (pos + 1) % rb.length;
            count += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this.getClass() != o.getClass()) {
            return false;
        } else if (this == o) {
            return true;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (this.fillCount != other.fillCount) {
            return false;
        }
        var iter1 = iterator();
        var iter2 = other.iterator();
        while (iter1.hasNext()) {
            if (iter1.next() != iter2.next()) {
                return false;
            }
        }
        return true;
    }
}
