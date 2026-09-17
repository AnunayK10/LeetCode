class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int MOD = 1000000007;
        
        // dp0[v] = count of sequences ending at v where the next move MUST be DOWN
        int[] dp0 = new int[m];
        
        // dp1[v] = count of sequences ending at v where the next move MUST be UP
        int[] dp1 = new int[m];
        
        // For length 1, any value can start a sequence, and it can go either UP or DOWN next
        for (int i = 0; i < m; i++) {
            dp0[i] = 1;
            dp1[i] = 1;
        }
        
        // Build the sequence up to length n
        for (int step = 2; step <= n; step++) {
            int[] nextDp0 = new int[m];
            int[] nextDp1 = new int[m];
            
            // To go UP to 'i' (next move DOWN), we sum dp1 for all valid previous values x < i.
            int prefixSum1 = 0;
            for (int i = 0; i < m; i++) {
                nextDp0[i] = prefixSum1;
                prefixSum1 = (prefixSum1 + dp1[i]) % MOD;
            }
            
            // To go DOWN to 'i' (next move UP), we sum dp0 for all valid previous values x > i.
            int suffixSum0 = 0;
            for (int i = m - 1; i >= 0; i--) {
                nextDp1[i] = suffixSum0;
                suffixSum0 = (suffixSum0 + dp0[i]) % MOD;
            }
            
            dp0 = nextDp0;
            dp1 = nextDp1;
        }
        
        // The result is the sum of all valid configurations of length n
        int totalValidArrays = 0;
        for (int i = 0; i < m; i++) {
            totalValidArrays = (totalValidArrays + dp0[i]) % MOD;
            totalValidArrays = (totalValidArrays + dp1[i]) % MOD;
        }
        
        return totalValidArrays;
    }
}