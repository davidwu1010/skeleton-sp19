import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private class Node {
        boolean isStringEnd;
        Map<Character, Node> children;

        Node(boolean isStringEnd) {
            this.isStringEnd = isStringEnd;
            this.children = new HashMap<>();
        }
    }

    private Node rootNode;

    public MyTrieSet() {
        this.rootNode = new Node(false);
    }

    @Override
    public void clear() {
        this.rootNode = new Node(false);
    }

    @Override
    public boolean contains(String key) {
        boolean isEnd = false;
        Node n = rootNode;

        for (int i = 0; i < key.length(); ++i) {
            if (n.children.containsKey(key.charAt(i))) {
                n = n.children.get(key.charAt(i));
                if (i == key.length() - 1) {
                    isEnd = n.isStringEnd;
                }
            } else {
                return false;
            }
        }

        return isEnd;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() == 0 || contains(key)) {
            return;
        }
        Node n = rootNode;

        for (int i = 0; i < key.length(); ++i) {
            if (!n.children.containsKey(key.charAt(i))) {
                n.children.put(key.charAt(i), new Node(false));
            }
            if (i == key.length() - 1) {
                n.children.get(key.charAt(i)).isStringEnd = true;
            }
            n = n.children.get(key.charAt(i));
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            return result;
        }

        Node n = rootNode;
        for (int i = 0; i < prefix.length(); ++i) {
            if (!n.children.containsKey(prefix.charAt(i))) {
                return result;
            }
            n = n.children.get(prefix.charAt(i));
        }

        helper(prefix, result, n);

        return result;
    }

    private void helper(String s, List<String> x, Node n) {
        if (n.isStringEnd) {
            x.add(s);
        }
        for (char c: n.children.keySet()) {
            helper(s + c, x, n.children.get(c));
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
