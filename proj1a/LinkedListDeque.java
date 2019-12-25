public class LinkedListDeque<T> {
    private static class Node<T> {
        Node<T> prev;
        T item;
        Node<T> next;
    }

    private int _size;
    private Node<T> head;

    public LinkedListDeque() {
        _size = 0;
        head = new Node<>();
        head.prev = head;
        head.next = head;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {}

    public void addFirst(T item) {
        Node<T> node = new Node<>();
        node.prev = head;
        node.item = item;
        node.next = head.next;

        head.next = node;
        node.next.prev = node;
    }

    public void addLast(T item) {
        Node<T> node = new Node<>();
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
        for (Node<T> p = head.next; p != head; p = p.next) {
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
        T item = head.next.item;
        head.next = head.next.next;
        head.next.prev = head;
        return item;
    }

    public T removeLast() {
        if (_size == 0) {
            return null;
        }
        T item = head.prev.item;
        head.prev = head.prev.prev;
        head.prev.next = head;
        return item;
    }

    public T get(int index) {
        if (index < 0 || index + 1 > _size ) {
            return null;
        }
        Node<T> p = head.next;
        while (index > 0) {
            p = p.next;
            --index;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return null;
    }
}
