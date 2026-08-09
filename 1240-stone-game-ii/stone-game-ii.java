import java.util.Arrays;

public class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        if (n == 0) return 0;

        // suffixSum[i] stores the total number of stones from index i to n-1
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Memoization table: i ranges from 0 to n, M ranges from 1 to n
        int[][] memo = new int[n][n + 1];

        return maxAliceStones(0, 1, suffixSum, memo, n);
    }

    private int maxAliceStones(int i, int M, int[] suffixSum, int[][] memo, int n) {
        // Base case: If the current player can take all remaining piles
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return cached result if already calculated
        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2M
        for (int X = 1; X <= 2 * M; X++) {
            // Stones captured = Total remaining stones - optimal stones opponent can get next turn
            int opponentStones = maxAliceStones(i + X, Math.max(M, X), suffixSum, memo, n);
            int currentTake = suffixSum[i] - opponentStones;
            
            maxStones = Math.max(maxStones, currentTake);
        }

        // Save result in memo table
        memo[i][M] = maxStones;
        return maxStones;
    }
}
