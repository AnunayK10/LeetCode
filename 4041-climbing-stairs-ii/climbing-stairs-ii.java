class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp0, dp1, dp2 represent the minimum costs to reach 
        // the previous 3 steps: step (j - 3), step (j - 2), and step (j - 1).
        // Initially, at j = 0: dp(0) = 0.
        // We initialize non-existent steps before 0 with a safe large value to prevent overflow.
        int INF = (int) 1e9;
        
        int dpMinus3 = INF; // step -2
        int dpMinus2 = INF; // step -1
        int dpMinus1 = 0;   // step 0 (starting point)

        for (int j = 1; j <= n; j++) {
            int stepCost = costs[j - 1];

            // Evaluate all three possible jump lengths: 1, 2, or 3 steps
            int from1 = dpMinus1 + 1; // jump of 1: (1)^2 = 1
            int from2 = dpMinus2 + 4; // jump of 2: (2)^2 = 4
            int from3 = dpMinus3 + 9; // jump of 3: (3)^2 = 9

            int currentDp = stepCost + Math.min(from1, Math.min(from2, from3));

            // Shift states forward
            dpMinus3 = dpMinus2;
            dpMinus2 = dpMinus1;
            dpMinus1 = currentDp;
        }

        return dpMinus1;
    }
}