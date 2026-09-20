class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp array to store the minimum path sum for the current row.
        // We use size n + 1 to simplify the calculation for the bottom row.
        int[] dp = new int[n + 1];
        
        // Start from the bottom row and work upwards to the top
        for (int row = n - 1; row >= 0; row--) {
            for (int i = 0; i <= row; i++) {
                // The optimal path from the current node is its own value plus 
                // the minimum of the two adjacent optimal paths below it.
                dp[i] = triangle.get(row).get(i) + Math.min(dp[i], dp[i + 1]);
            }
        }
        
        // The peak of the triangle now holds the minimum path sum
        return dp[0];
    }
}