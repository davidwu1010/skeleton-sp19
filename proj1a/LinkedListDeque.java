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
        head = new Node<T>();
        head.prev = head;
        head.next = head;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {}

    public void addFirst(T item) {}

    public void addLast(T item) {}

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
        return null;
    }

    public T removeLast() {
        return null;
    }

    public T get(int index) {
        return null;
    }

    public T getRecursive(int index) {
        return null;
    }
}
