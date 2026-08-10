public class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] indicates if the player going first with i stones can force a win
        boolean[] dp = new boolean[n + 1];

        // Iteratively compute game states from 1 to n
        for (int i = 1; i <= n; i++) {
            // Try taking k^2 stones away
            for (int k = 1; k * k <= i; k++) {
                // If taking k^2 stones leaves the opponent in a losing state, 
                // the current player wins optimally.
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // Optimization: Found a winning move, no need to check further squares
                }
            }
        }

        return dp[n];
    }
}
