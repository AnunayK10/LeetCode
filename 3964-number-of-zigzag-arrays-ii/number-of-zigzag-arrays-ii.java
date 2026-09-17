class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;
        
        // T[i][j] stores the number of valid transitions from state j to state i
        long[][] T = new long[size][size];

        // 1. From "next DOWN" states, move to a strictly smaller value, now becoming "next UP"
        for (int v = 0; v < m; v++) {
            for (int u = 0; u < v; u++) {
                T[u + m][v] = 1;
            }
        }

        // 2. From "next UP" states, move to a strictly larger value, now becoming "next DOWN"
        for (int v = 0; v < m; v++) {
            for (int u = v + 1; u < m; u++) {
                T[u][v + m] = 1;
            }
        }

        // Compute T^(n-1)
        long[][] T_pow = matrixPower(T, n - 1, size);

        // The answer is the sum of all elements in T^(n-1)
        long totalValidArrays = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totalValidArrays = (totalValidArrays + T_pow[i][j]) % MOD;
            }
        }

        return (int) totalValidArrays;
    }

    // Binary matrix exponentiation to calculate base^exp in O(log(exp))
    private long[][] matrixPower(long[][] base, long exp, int size) {
        long[][] res = new long[size][size];
        
        // Initialize the result as an identity matrix
        for (int i = 0; i < size; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if (exp % 2 == 1) {
                res = multiplyMatrix(res, base, size);
            }
            base = multiplyMatrix(base, base, size);
            exp /= 2;
        }
        
        return res;
    }

    // Multiplies two matrices modulo 10^9 + 7
    private long[][] multiplyMatrix(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue; // Optimization to skip 0 multiplications
                
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        
        return C;
    }
}