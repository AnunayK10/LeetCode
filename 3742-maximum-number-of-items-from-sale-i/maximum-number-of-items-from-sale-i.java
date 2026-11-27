class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 1;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // multCount[f] = total items whose factor is a multiple of f
        int[] multCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                multCount[f] += freq[m];
            }
        }

        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) {
                continue;
            }

            // Buying the 1st copy grants 1 bought item + (multCount[factor] - 1) free items
            int firstCopyGain = 1 + (multCount[factor] - 1);

            // 1) 0/1 step: activate this item once (first copy + free copies)
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }

            // 2) Unbounded step: buy additional copies (+1 item per copy, no extra freebies)
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }

        return dp[budget];
    }
}