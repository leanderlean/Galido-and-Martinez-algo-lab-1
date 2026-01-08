package net.javaguides;
import java.util.Scanner;
import java.util.Random;



// The main class
public class PercolationStats {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter table dimension (n): ");
        int n = sc.nextInt(); 
        
        boolean[][] table = new boolean[n][n];
        Random random = new Random();

        // Open random sites until it percolates (or a set amount)
        int openedCount = 0;
        Percolation perc = new Percolation();

        // Let's open sites one by one until it actually percolates!
        while (!perc.percolates(table, n)) {
            int row = random.nextInt(n);
            int col = random.nextInt(n);
            if (!table[row][col]) {
                table[row][col] = true;
                openedCount++;
            }
        }

        boolean[][] path = perc.getPercolatedPath();

        // Print final grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (path != null && path[i][j]) {
                    System.out.print("P ");
                } else {
                    System.out.print(table[i][j] ? "1 " : "0 ");
                }
            }
            System.out.println();
        }

        System.out.println("System percolated after opening " + openedCount + " sites.");
    }
}