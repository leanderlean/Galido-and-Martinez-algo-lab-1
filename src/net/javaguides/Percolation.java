package net.javaguides;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private boolean[][] openSites;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private int virtualBottom;

    // Constructor: create N-by-N grid, all blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N must be > 0");
        this.N = N;
        openSites = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2); // +2 for virtual top and bottom
        virtualTop = N * N;
        virtualBottom = N * N + 1;

        // Connect virtual top to top row, virtual bottom to bottom row
        for (int col = 0; col < N; col++) {
            uf.union(virtualTop, xyTo1D(0, col));
            uf.union(virtualBottom, xyTo1D(N - 1, col));
        }
    }

    // Convert 2D coordinates to 1D index
    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    // Open a site and connect to neighbors
    public void open(int row, int col) {
        if (!isValid(row, col)) throw new IndexOutOfBoundsException();
        if (openSites[row][col]) return;
        openSites[row][col] = true;

        // Connect to open neighbors
        if (row > 0 && isOpen(row - 1, col)) uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        if (row < N - 1 && isOpen(row + 1, col)) uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        if (col > 0 && isOpen(row, col - 1)) uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        if (col < N - 1 && isOpen(row, col + 1)) uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
    }

    // Is the site open?
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) throw new IndexOutOfBoundsException();
        return openSites[row][col];
    }

    // Is the site full (connected to top)?
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) throw new IndexOutOfBoundsException();
        return uf.connected(virtualTop, xyTo1D(row, col));
    }

    // Does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    // Check valid indices
    private boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}
