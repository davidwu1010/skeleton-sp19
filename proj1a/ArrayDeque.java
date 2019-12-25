public class ArrayDeque<T> {
    private int _size;
    private int capacity;
    private int front;
    private int end;
    private T[] items;

    public ArrayDeque() {
        _size = 0;
        capacity = 8;
        front = 0;
        end = 0;
        items = (T[]) new Object[capacity];
    }

    public ArrayDeque(ArrayDeque other) {
        _size = other._size;
        capacity = other.capacity;
        front = other.front;
        end = other.end;
        items = (T[]) new Object[capacity];
        System.arraycopy(other.items, 0, items, 0, capacity);
    }

    public void addFirst(T item) {
        if (size() == capacity) {
            grow();
        }
        if (_size == 0) {
            items[front] = item;
        } else {
            front = Math.floorMod(front - 1, capacity);
            items[front] = item;
        }
        _size += 1;
    }

    public void addLast(T item) {
        if (size() == capacity) {
            grow();
        }
        end = (end + 1) % capacity;
        items[end] = item;
        _size += 1;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public int size() {
        return _size;
    }

    public void printDeque() {
        for (int i = 0; i < size(); ++i) {
            if (i == 0) {
                System.out.printf("%s", get(i));
            } else {
                System.out.printf(" %s", get(i));
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[front];
        items[front] = null;
        front = (front + 1) % capacity;
        _size -= 1;
        if (capacity >= 16 && (double) _size / capacity < 0.25) {
            shrink();
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int index = Math.floorMod(end - 1, capacity);
        T item = items[index];
        items[index] = null;
        end = index;
        _size -= 1;
        if (capacity >= 16 && (double) _size / capacity < 0.25) {
            shrink();
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index + 1 > size()) {
            return null;
        }
        return items[(front + index) % capacity];
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[capacity / 2];
        for (int i = 0; i < _size; ++i) {
            newArray[i] = get(i);
        }
        items = newArray;
        capacity /= 2;
        front = 0;
        end = _size;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[capacity * 2];
        for (int i = 0; i < _size; ++i) {
            newArray[i] = get(i);
        }
        items = newArray;
        capacity *= 2;
        front = 0;
        end = _size;
    }
}
