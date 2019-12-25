public class LinkedListDeque<T> {
    private class Node {
        Node prev;
        T item;
        Node next;
    }

    private int _size;
    private final Node head;

    public LinkedListDeque() {
        _size = 0;
        head = new Node();
        head.prev = head;
        head.next = head;
    }

    public LinkedListDeque(LinkedListDeque other) {
        _size = 0;
        head = new Node();

        for (int i = 0; i < other._size; ++i) {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        _size += 1;
        Node node = new Node();
        node.prev = head;
        node.item = item;
        node.next = head.next;

        head.next = node;
        node.next.prev = node;
    }

    public void addLast(T item) {
        _size += 1;
        Node node = new Node();
        node.prev = head.prev;
        node.item = item;
        node.next = head;

        head.prev = node;
        node.prev.next = node;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public int size() {
        return _size;
    }

    public void printDeque() {
        for (Node p = head.next; p != head; p = p.next) {
            if (p == head.next) {
                System.out.printf("%s", p.item);
            } else {
                System.out.printf(" %s", p.item);
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (_size == 0) {
            return null;
        }
        _size -= 1;
        T item = head.next.item;
        head.next = head.next.next;
        head.next.prev = head;
        return item;
    }

    public T removeLast() {
        if (_size == 0) {
            return null;
        }
        _size -= 1;
        T item = head.prev.item;
        head.prev = head.prev.prev;
        head.prev.next = head;
        return item;
    }

    public T get(int index) {
        if (index < 0 || index + 1 > _size ) {
            return null;
        }
        Node p = head.next;
        while (index > 0) {
            p = p.next;
            --index;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index + 1 > _size) {
            return null;
        }
        return getRecursive(index, head);
    }

    private T getRecursive(int index, Node head) {
        if (index == 0) {
            return head.item;
        }
        return getRecursive(index - 1, head.next);
    }
}
