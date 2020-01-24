import java.util.LinkedList;

public class BubbleGrid {
    private int[][] grid;
    final int M;
    final int N;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        M = grid.length; // row number
        N = grid[0].length; // col number
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        UnionFind uf = new UnionFind(M * N + 1);  // plus 1 for the virtual head node

        // copy grid
        int[][] gridCopy = new int[M][];
        for (int i = 0; i < M; ++i) {
            gridCopy[i] = new int[N];
            for (int j = 0; j < N; ++j) {
                gridCopy[i][j] = grid[i][j];
            }
        }

        // throw darts
        for (int[] dart: darts) {
            int row = dart[0];
            int col = dart[1];
            gridCopy[row][col] = 0;
        }

        // connect first row to the head node
        for (int i = 0; i < N; ++i) {
            if (gridCopy[0][i] == 1) {
                uf.union(head(), toId(0, i));
            }
        }

        // connect the rest
        for (int i = 1; i < M; ++i) {
            if (gridCopy[i][0] == 1 && gridCopy[i - 1][0] == 1) {
                uf.union(toId(i, 0), toId(i - 1, 0));
            }
            for (int j = 1; j < N; ++j) {
                if (gridCopy[i][j] == 1) {
                    if (gridCopy[i - 1][j] == 1) {
                        uf.union(toId(i, j), toId(i - 1, j));
                    }
                    if (gridCopy[i][j - 1] == 1) {
                        uf.union(toId(i, j), toId(i, j - 1));
                    }
                }
            }
        }

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = darts.length - 1; i >= 0; --i) {
            int[] dart = darts[i];
            int row = dart[0];
            int col = dart[1];

            if (grid[row][col] == 1) {
                gridCopy[row][col] = 1;
                int sizeBefore = uf.sizeOf(head());

                if (row == 0) uf.union(toId(row, col), head());
                if (row > 0 && gridCopy[row - 1][col] == 1) uf.union(toId(row, col), toId(row - 1, col));
                if (row < M - 1 && gridCopy[row + 1][col] == 1) uf.union(toId(row, col), toId(row + 1, col));
                if (col > 0 && gridCopy[row][col - 1] == 1) uf.union(toId(row, col), toId(row, col - 1));
                if (col < N - 1 && gridCopy[row][col + 1] == 1) uf.union(toId(row, col), toId(row, col + 1));

                int sizeAfter = uf.sizeOf(head());
                list.addFirst(Math.max(sizeAfter - sizeBefore - 1, 0));
            } else {
                list.addFirst(0);
            }
        }

        int[] res = new int[darts.length];
        int i = 0;
        for (var e: list) {
            res[i] = e;
            ++i;
        }

        return res;
    }

    private int toId(int row, int col) {
        return row * N + col;
    }

    private int head() {
        return M * N;
    }
}
