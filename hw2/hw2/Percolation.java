package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int numOpen;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int N;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal grid size");
        }
        grid = new int[N][];
        for (int i = 0; i < N; ++i) {
            grid[i] = new int[N];
        }
        numOpen = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        grid[row][col] = 1;
        ++numOpen;
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(convertToId(row, col), convertToId(row - 1, col));
            uf2.union(convertToId(row, col), convertToId(row - 1, col));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            uf.union(convertToId(row, col), convertToId(row + 1, col));
            uf2.union(convertToId(row, col), convertToId(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(convertToId(row, col), convertToId(row, col - 1));
            uf2.union(convertToId(row, col), convertToId(row, col - 1));
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            uf.union(convertToId(row, col), convertToId(row, col + 1));
            uf2.union(convertToId(row, col), convertToId(row, col + 1));
        }
        if (row == 0) {
            uf.union(convertToId(row, col), N * N);
            uf2.union(convertToId(row, col), N * N);
        }
        if (row == N - 1) {
            uf.union(convertToId(row, col), N * N + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf2.connected(convertToId(row, col), N * N);
    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean percolates() {
        return uf.connected(N * N, N * N + 1);
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    private int convertToId(int row, int col) {
        return row * N + col;
    }
}
