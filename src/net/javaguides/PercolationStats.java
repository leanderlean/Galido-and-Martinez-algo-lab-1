package net.javaguides;
import java.util.Scanner;
import java.util.Random;



// The main class
public class PercolationStats {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 10;
        
        boolean[][] table = new boolean[n][n];
        Random random = new Random();

        System.out.println("Initial grid:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("0 ");
            }
            System.out.println();
        }

        System.out.print("Enter number of blocks to open: ");
        int numToOpen = sc.nextInt();

        // Open random sites based on user input
        int openedCount = 0;

        while (openedCount < numToOpen && openedCount < n * n) {
            int row = random.nextInt(n);
            int col = random.nextInt(n);
            if (!table[row][col]) {
                table[row][col] = true;
                openedCount++;
            }
        }
        
        Percolation perc = new Percolation();
        boolean percolates = perc.percolates(table, n);
        boolean[][] path = perc.getPercolatedPath();

        // Print final grid
        System.out.println("Final grid:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (percolates && path != null && path[i][j]) {
                    System.out.print("P ");
                } else {
                    System.out.print(table[i][j] ? "1 " : "0 ");
                }
            }
            System.out.println();
        }

        if (percolates) {
            System.out.println("System percolated after opening " + openedCount + " sites.");
        } else {
            System.out.println("System did NOT percolate after opening " + openedCount + " sites.");
        }
    }
}