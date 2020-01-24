import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;

        public TreeNode(K key, V value, TreeNode left, TreeNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode root;
    private int size;

    public BSTMap() {
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return containsKey(root, key);
    }

    private boolean containsKey(TreeNode root, K key) {
        if (root == null) {
            return false;
        } else if (root.key.equals(key)) {
            return true;
        } else if (root.key.compareTo(key) > 0) {
            return containsKey(root.left, key);
        } else {
            return containsKey(root.right, key);
        }
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(TreeNode root, K key) {
        if (root == null) {
            return null;
        } else if (root.key.compareTo(key) == 0) {
            return root.value;
        } else if (root.key.compareTo(key) > 0) {
            return get(root.left, key);
        } else {
            return get(root.right, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        TreeNode newRoot = put(root, key, value);
        if (root == null) {
            root = newRoot;
        }
    }

    private TreeNode put(TreeNode root, K key, V value) {
        if (root == null) {
            ++size;
            return new TreeNode(key, value, null, null);
        } else if (root.key.compareTo(key) == 0) {
            root.value = value;
        } else if(root.key.compareTo(key) > 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }
        return root;
    }

    @Override
    public Set<K> keySet() {
        Set<K> res = new TreeSet<>();
        keySet(root, res);
        return res;
    }

    private void keySet(TreeNode root, Set<K> res) {
        if (root == null) {
            return;
        }
        res.add(root.key);
        keySet(root.left, res);
        keySet(root.right, res);
    }

    public void printInOrder() {
        print(root);
        System.out.println();
    }

    private void print(TreeNode root) {
        if (root == null) return;
        print(root.left);
        System.out.print(root.key.toString() + ": " + root.value.toString() + ", ");
        print(root.right);
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
        throw new UnsupportedOperationException();
    }
}
