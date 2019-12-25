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
        return false;
    }
    public int size() {
        return 0;
    }
    public void printDeque() {}
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
