package net.javaguides;

public class Percolation {
    private boolean[][] percolatedPath;

    public boolean[][] getPercolatedPath() {
        return percolatedPath;
    }

    public boolean percolates(boolean[][] grid, int n) {
        percolatedPath = null;
        for (int col = 0; col < n; col++) {
            if (grid[0][col]) { 
                boolean[][] visited = new boolean[n][n];
                if (dfs(grid, visited, 0, col, n)) {
                    percolatedPath = visited;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(boolean[][] grid, boolean[][] visited, int row, int col, int n) {
        if (row < 0 || row >= n || col < 0 || col >= n || !grid[row][col] || visited[row][col]) {
            return false;
        }

        visited[row][col] = true;

        if (row == n - 1) return true;

        return dfs(grid, visited, row + 1, col, n) || 
               dfs(grid, visited, row - 1, col, n) ||
               dfs(grid, visited, row, col + 1, n) ||
               dfs(grid, visited, row, col - 1, n);
    }
}
