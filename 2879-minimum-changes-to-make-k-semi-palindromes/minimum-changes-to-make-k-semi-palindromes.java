class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        // Precompute proper divisors for every possible substring length (2 to n)
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d * d <= len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                    // Add paired divisor if it's strictly less than len and distinct
                    if (d != 1 && d * d != len) {
                        divisors[len].add(len / d);
                    }
                }
            }
        }

        // cost[i][j]: min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        int INF = 1_000_000;
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                for (int d : divisors[len]) {
                    cost[i][j] = Math.min(cost[i][j], getChanges(chars, i, j, d));
                }
            }
        }

        // dp[parts][end]: min changes to partition s[0..end] into 'parts' semi-palindromes
        int[][] dp = new int[k + 1][n];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        // Base case: 1 partition covering s[0..j]
        for (int j = 1; j < n; j++) {
            dp[1][j] = cost[0][j];
        }

        // Fill DP table for 2 up to k partitions
        for (int parts = 2; parts <= k; parts++) {
            // Each part needs at least 2 characters, so 'end' starts at 2 * parts - 1
            for (int end = 2 * parts - 1; end < n; end++) {
                // 'prevEnd' is the end index of the previous (parts - 1) partitions
                for (int prevEnd = 2 * (parts - 1) - 1; prevEnd <= end - 2; prevEnd++) {
                    if (dp[parts - 1][prevEnd] != INF) {
                        dp[parts][end] = Math.min(dp[parts][end], dp[parts - 1][prevEnd] + cost[prevEnd + 1][end]);
                    }
                }
            }
        }

        return dp[k][n - 1];
    }

    // Helper to compute character mismatches across all 'd' modulo groups in s[start..end]
    private int getChanges(char[] s, int start, int end, int d) {
        int changes = 0;
        int len = end - start + 1;
        int groupLen = len / d;

        for (int offset = 0; offset < d; offset++) {
            int left = start + offset;
            int right = start + offset + (groupLen - 1) * d;
            while (left < right) {
                if (s[left] != s[right]) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}