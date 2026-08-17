public class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if (n <= 1) return 0;

        // 1. Build Prefix Sum Array for O(1) interval sum lookups
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + stoneValue[i];
        }

        // dp[i][j] stores the max score Alice can get from stoneValue[i...j]
        int[][] dp = new int[n][n];

        // 2. Iterate through all possible subarray lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                // Try every possible split point k between i and j-1
                for (int k = i; k < j; k++) {
                    int leftSum = pref[k + 1] - pref[i];
                    int rightSum = pref[j + 1] - pref[k + 1];

                    if (leftSum < rightSum) {
                        // Keep left group
                        dp[i][j] = Math.max(dp[i][j], leftSum + dp[i][k]);
                    } else if (leftSum > rightSum) {
                        // Keep right group
                        dp[i][j] = Math.max(dp[i][j], rightSum + dp[k + 1][j]);
                    } else {
                        // Sums are equal: Alice can choose to keep either the left or right side
                        int maxChoice = Math.max(dp[i][k], dp[k + 1][j]);
                        dp[i][j] = Math.max(dp[i][j], leftSum + maxChoice);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
