package net.javaguides;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private final int topVirtual;
    private final int bottomVirtual;
    private int openCount = 0;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N must be > 0");
        n = N;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        topVirtual = n * n;
        bottomVirtual = n * n + 1;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n)
            throw new IndexOutOfBoundsException("row or col out of bounds");
    }

    private int xyTo1D(int row, int col) {
        return row * n + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (grid[row][col]) return;

        grid[row][col] = true;
        openCount++;

        int index = xyTo1D(row, col);

        if (row == 0) uf.union(index, topVirtual);
        if (row == n - 1) uf.union(index, bottomVirtual);

        if (row > 0 && isOpen(row - 1, col)) uf.union(index, xyTo1D(row - 1, col));
        if (row < n - 1 && isOpen(row + 1, col)) uf.union(index, xyTo1D(row + 1, col));
        if (col > 0 && isOpen(row, col - 1)) uf.union(index, xyTo1D(row, col - 1));
        if (col < n - 1 && isOpen(row, col + 1)) uf.union(index, xyTo1D(row, col + 1));
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(xyTo1D(row, col), topVirtual);
    }

    public boolean percolates() {
        return uf.connected(topVirtual, bottomVirtual);
    }

    public int numberOfOpenSites() {
        return openCount;
    }
}
