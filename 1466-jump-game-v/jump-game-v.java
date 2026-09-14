class Solution {
    int[] dp;
    
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        dp = new int[n];
        int maxVisited = 1;
        
        // Try starting from every index and find the maximum jumps possible
        for (int i = 0; i < n; i++) {
            maxVisited = Math.max(maxVisited, dfs(arr, d, i));
        }
        
        return maxVisited;
    }
    
    private int dfs(int[] arr, int d, int i) {
        // If we have already computed the result for this index, return it
        if (dp[i] != 0) {
            return dp[i];
        }
        
        int maxJumpsFromI = 1; // We can at least visit the starting index itself
        
        // Check jumps to the right
        for (int j = i + 1; j <= Math.min(i + d, arr.length - 1); j++) {
            if (arr[j] >= arr[i]) {
                break; // Blocked by a taller or equal bar
            }
            maxJumpsFromI = Math.max(maxJumpsFromI, 1 + dfs(arr, d, j));
        }
        
        // Check jumps to the left
        for (int j = i - 1; j >= Math.max(i - d, 0); j--) {
            if (arr[j] >= arr[i]) {
                break; // Blocked by a taller or equal bar
            }
            maxJumpsFromI = Math.max(maxJumpsFromI, 1 + dfs(arr, d, j));
        }
        
        // Memoize and return
        dp[i] = maxJumpsFromI;
        return maxJumpsFromI;
    }
}