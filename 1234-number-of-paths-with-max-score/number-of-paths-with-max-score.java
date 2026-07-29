import java.util.List;

public class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        int[][] dp = new int[n][2];
        for (int j = 0; j < n; j++) {
            dp[j][0] = -1;
            dp[j][1] = 0;
        }
        dp[n - 1][0] = 0;
        dp[n - 1][1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            String rowStr = board.get(i);
            int next_score = -1; 
            int next_count = 0;
            for (int j = n - 1; j >= 0; j--) {
                char cell = rowStr.charAt(j);
                int prev_dp_score = dp[j][0];
                int prev_dp_count = dp[j][1];
                if (cell == 'X' || (i == n - 1 && j == n - 1)) {
                    next_score = dp[j][0];
                    next_count = dp[j][1];
                    if (cell == 'X') {
                        dp[j][0] = -1;
                        dp[j][1] = 0;
                    }
                    continue;
                }
                int maxScore = -1;
                int pathCount = 0;
                if (dp[j][0] != -1) {
                    maxScore = dp[j][0];
                    pathCount = dp[j][1];
                }
                if (next_score != -1) {
                    if (next_score > maxScore) {
                        maxScore = next_score;
                        pathCount = next_count;
                    } else if (next_score == maxScore) {
                        pathCount = (pathCount + next_count) % MOD;
                    }
                }
                int diag_score = (j + 1 < n) ? prev_dp_score : -1; 
            }
        }
        return getPathsWithMaxScoreOptimized(board, n, MOD);
    }
    private int[] getPathsWithMaxScoreOptimized(List<String> board, int n, int MOD) {
        int[][][] dp = new int[2][n][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = -1;
            }
        }
        dp[(n - 1) % 2][n - 1][0] = 0;
        dp[(n - 1) % 2][n - 1][1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            int currRow = i % 2;
            int nextRow = (i + 1) % 2;
            String rowStr = board.get(i);
            for (int j = n - 1; j >= 0; j--) {
                char cell = rowStr.charAt(j);
                if (cell == 'X' || (i == n - 1 && j == n - 1)) {
                    if (cell == 'X') {
                        dp[currRow][j][0] = -1;
                        dp[currRow][j][1] = 0;
                    }
                    continue;
                }
                int maxScore = -1;
                int pathCount = 0;
                int[][] neighbors = {
                    {dp[nextRow][j][0], dp[nextRow][j][1]}, 
                    {j + 1 < n ? dp[currRow][j + 1][0] : -1, j + 1 < n ? dp[currRow][j + 1][1] : 0}, 
                    {j + 1 < n ? dp[nextRow][j + 1][0] : -1, j + 1 < n ? dp[nextRow][j + 1][1] : 0}  
                };
                for (int[] neighbor : neighbors) {
                    int nScore = neighbor[0];
                    int nCount = neighbor[1];
                    if (nScore != -1) {
                        if (nScore > maxScore) {
                            maxScore = nScore;
                            pathCount = nCount;
                        } else if (nScore == maxScore) {
                            pathCount = (pathCount + nCount) % MOD;
                        }
                    }
                }
                if (maxScore != -1) {
                    int value = (cell == 'E') ? 0 : (cell - '0');
                    dp[currRow][j][0] = maxScore + value;
                    dp[currRow][j][1] = pathCount;
                } else {
                    dp[currRow][j][0] = -1;
                    dp[currRow][j][1] = 0;
                }
            }
        }
        int finalScore = dp[0][0][0];
        int finalCount = dp[0][0][1];
        return finalScore == -1 ? new int[]{0, 0} : new int[]{finalScore, finalCount};
    }
}
