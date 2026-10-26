class Solution {
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    // Compute the total value of the current island using DFS
                    long islandSum = dfs(grid, i, j);
                    
                    // Check if the total sum of the island is divisible by k
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        // Boundary check and check if the cell is water or already visited
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 0) {
            return 0;
        }

        long currentVal = grid[r][c];
        // Mark the current cell as visited by setting it to 0
        grid[r][c] = 0;

        // Explore all 4 adjacent directions (Up, Down, Left, Right)
        currentVal += dfs(grid, r - 1, c);
        currentVal += dfs(grid, r + 1, c);
        currentVal += dfs(grid, r, c - 1);
        currentVal += dfs(grid, r, c + 1);

        return currentVal;
    }
}