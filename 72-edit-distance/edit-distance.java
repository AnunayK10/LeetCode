class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[] dp = new int[n + 1];
        
        // Base case: converting empty string to word2 prefix
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            int prev = dp[0]; // Stores dp[i-1][j-1]
            dp[0] = i;        // Base case: converting word1 prefix to empty string
            
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev; // Characters match, no operation needed
                } else {
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j - 1]));
                    // 1 + min(replace (prev), delete (dp[j]), insert (dp[j-1]))
                }
                prev = temp;
            }
        }
        
        return dp[n];
    }
}