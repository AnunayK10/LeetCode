class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007L;

        // go[i][j][0] stores the final landing position (r, c) when moving right from (i, j)
        // go[i][j][1] stores the final landing position (r, c) when moving down from (i, j)
        int[][][] go = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Precompute for move right (direction 0)
                int r = i, c = j + 1;
                int dir = 0; // 0: right, 1: down
                boolean valid = true;
                
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        // Moving right into mirror -> turns down
                        dir = 1;
                        r += 1;
                    } else {
                        // Moving down into mirror -> turns right
                        dir = 0;
                        c += 1;
                    }
                }
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    go[i][j][0] = -1; // Out of bounds
                } else {
                    go[i][j][0] = r * n + c; // Encoded cell index
                }

                // Precompute for move down (direction 1)
                r = i + 1; 
                c = j;
                dir = 1;
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        dir = 1;
                        r += 1;
                    } else {
                        dir = 0;
                        c += 1;
                    }
                }
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    go[i][j][1] = -1;
                } else {
                    go[i][j][1] = r * n + c;
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // 1. Try moving right
                int targetRight = go[i][j][0];
                if (targetRight != -1) {
                    int nr = targetRight / n;
                    int nc = targetRight % n;
                    dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD;
                }

                // 2. Try moving down
                int targetDown = go[i][j][1];
                if (targetDown != -1) {
                    int nr = targetDown / n;
                    int nc = targetDown % n;
                    dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}