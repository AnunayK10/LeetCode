class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        
        // Total points we are choosing from = n + k - 1
        int N = n + k - 1;
        // Total endpoints we need to choose = 2 * k
        int K = 2 * k;
        
        // If we need to pick more points than available, it's impossible.
        if (K > N) return 0;
        
        long numerator = 1;
        long denominator = 1;
        
        // Calculate (N! / (N-K)!) % MOD and (K!) % MOD
        for (int i = 1; i <= K; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        
        // Fermat's Little Theorem for modular division
        // (numerator / denominator) % MOD == (numerator * modularInverse(denominator)) % MOD
        long ans = (numerator * modInverse(denominator, MOD)) % MOD;
        
        return (int) ans;
    }
    
    // Calculates base^exp % mod
    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
    
    // Calculates the modular inverse using Fermat's Little Theorem
    private long modInverse(long n, long m) {
        return power(n, m - 2, m);
    }
}