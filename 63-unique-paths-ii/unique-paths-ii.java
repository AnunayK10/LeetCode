class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[] dp = new int[n];
        // If the start cell has an obstacle, 0 paths are possible
        dp[0] = (obstacleGrid[0][0] == 1) ? 0 : 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // Obstacle blocks all paths through this cell
                } else if (j > 0) {
                    dp[j] += dp[j - 1]; // Ways from top (dp[j]) + ways from left (dp[j-1])
                }
            }
        }
        
        return dp[n - 1];
    }
}