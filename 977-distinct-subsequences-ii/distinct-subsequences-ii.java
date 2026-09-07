class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // dp[i] will store the number of distinct subsequences ending with the (i)th character of the alphabet
        int[] dp = new int[26];
        int total = 0;
        
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            
            // The number of new subsequences we can form ending with 'c'
            // is (total + 1) - (previous count of subsequences ending in 'c')
            int added = (total + 1 - dp[index]) % MOD;
            
            // In Java, modulo of a negative number can be negative, so we adjust it
            if (added < 0) {
                added += MOD;
            }
            
            // Update the count for the current character
            dp[index] = (dp[index] + added) % MOD;
            
            // Update the total distinct subsequences
            total = (total + added) % MOD;
        }
        
        return total;
    }
}