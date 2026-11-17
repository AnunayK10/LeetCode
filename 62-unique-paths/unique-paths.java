class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        // There is only 1 way to reach any cell in the first row
        java.util.Arrays.fill(dp, 1);
        
        // Compute paths row by row from the second row onwards
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] comes from the top cell, and dp[j-1] comes from the left cell
                dp[j] += dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
}