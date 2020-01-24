import java.util.Arrays;

public class UnionFind {

    private int vertices[];
    public int size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        vertices = new int[n];
        Arrays.fill(vertices, -1);
        size = n;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex > vertices.length - 1) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -vertices[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return vertices[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) {
            return;
        }

        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size1 <= size2) {
            vertices[root2] += vertices[root1];
            vertices[root1] = root2;
        } else {
            vertices[root1] += vertices[root2];
            vertices[root2] = root1;
        }
        --size;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int root = vertex;
        while (parent(root) >= 0) {
            root = parent(root);
        }

        while (vertex != root) {
            int temp = vertices[vertex];
            vertices[vertex] = root;
            vertex = temp;
        }

        return root;
    }
}
