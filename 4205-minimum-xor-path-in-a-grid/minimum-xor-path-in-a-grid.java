
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX_XOR = 1024;
        
        // dp[j] is a BitSet representing all possible XOR values at column j
        BitSet[] dp = new BitSet[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new BitSet(MAX_XOR);
        }
        
        dp[0].set(grid[0][0]);
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            for (int x = dp[j - 1].nextSetBit(0); x >= 0; x = dp[j - 1].nextSetBit(x + 1)) {
                dp[j].set(x ^ grid[0][j]);
            }
        }
        
        // Process remaining rows
        for (int i = 1; i < m; i++) {
            // First element of row i comes only from top
            BitSet nextCol = new BitSet(MAX_XOR);
            for (int x = dp[0].nextSetBit(0); x >= 0; x = dp[0].nextSetBit(x + 1)) {
                nextCol.set(x ^ grid[i][0]);
            }
            dp[0] = nextCol;
            
            for (int j = 1; j < n; j++) {
                BitSet newDp = new BitSet(MAX_XOR);
                // Combine states from top (dp[j]) and left (dp[j-1])
                BitSet combined = (BitSet) dp[j].clone();
                combined.or(dp[j - 1]);
                
                for (int x = combined.nextSetBit(0); x >= 0; x = combined.nextSetBit(x + 1)) {
                    newDp.set(x ^ grid[i][j]);
                }
                dp[j] = newDp;
            }
        }
        
        return dp[n - 1].nextSetBit(0);
    }
}