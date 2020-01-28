import java.sql.ResultSet;
import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private double loadFactor;
    private int bucketSize;
    private int size;
    private ArrayList<LinkedList<Node>> buckets;
    private HashSet<K> keys;

    private class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (o.getClass() != this.getClass()) {
                return false;
            }
            Node other = (Node) o;
            return this.key == other.key;
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.loadFactor = loadFactor;
        this.bucketSize = initialSize;
        this.size = 0;
        this.buckets = new ArrayList<>(initialSize);
        this.keys = new HashSet<>();

        for (int i = 0; i < initialSize; ++i) {
            buckets.add(new LinkedList<>());
        }
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < buckets.size(); ++i) {
            buckets.set(i, null);
        }
        keys.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        if (!keys.contains(key)) {
            return null;
        }
        var list = buckets.get(hash(key.hashCode()));
        for (Node node: list) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void put(K key, V value) {
        if (keys.contains(key)) {
            var list = buckets.get(hash(key.hashCode()));
            list.remove(new Node(key, null));
            list.add(new Node(key, value));
        } else {
            buckets.get(hash(key.hashCode())).addLast(new Node(key, value));
            keys.add(key);
            ++size;
        }
        if ((double) (size / bucketSize) > loadFactor) {
            resize();
        }
    }

    private void resize() {
        bucketSize *= 2;
        ArrayList<LinkedList<Node>> newBuckets = new ArrayList<>(bucketSize);

        for (int i = 0; i < bucketSize; ++i) {
            newBuckets.add(new LinkedList<>());
        }

        for (var list: buckets) {
            for (var node: list) {
                newBuckets.get(hash(node.key.hashCode())).add(node);
            }
        }

        buckets = newBuckets;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    private int hash(Object o) {
        return (o.hashCode() & 0x7FFFFFFF) % bucketSize;
    }
}
