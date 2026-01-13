package net.javaguides;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] thresholds;
    private final int trials;

    // Perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N and T must be greater than 0");

        trials = T;
        thresholds = new double[T];

        for (int t = 0; t < T; t++) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                }
            }
            thresholds[t] = (double) perc.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    // Main method now takes N and T as command-line arguments
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java net.javaguides.PercolationStats <grid size N> <trials T>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLow() + ", " + stats.confidenceHigh() + "]");
    }
}
