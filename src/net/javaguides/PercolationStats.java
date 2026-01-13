package net.javaguides;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Scanner;

public class PercolationStats {
    private final double[] thresholds;
    private final int trials;

    // Constructor: performs T experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N and T must be > 0");

        trials = T;
        thresholds = new double[T];

        for (int t = 0; t < T; t++) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!perc.isOpen(row, col))
                    perc.open(row, col);
            }
            thresholds[t] = (double) perc.numberOfOpenSites() / (N * N);
        }
    }

    // Sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // Sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // Low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    // High endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    // Main: prompts user for N and T
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter grid size N: ");
        int N = sc.nextInt();

        System.out.print("Enter number of trials T: ");
        int T = sc.nextInt();

        PercolationStats stats = new PercolationStats(N, T);

        System.out.println("\nResults:");
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLow() + ", " + stats.confidenceHigh() + "]");
    }
}
