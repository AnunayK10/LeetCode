class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        // Count how many items have each factor value
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // For each factor f, count total items whose factor is a multiple of f
        // Subtract 1 to exclude one item of factor f itself (j != i)
        int[] multCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            int count = 0;
            for (int m = f; m <= maxFactor; m += f) {
                count += freq[m];
            }
            multCount[f] = Math.max(0, count - 1);
        }

        // Candidate bonus batches: {price, bonusCopies}
        // Only items with price <= 2 * minPrice can beat buying plain copies at minPrice
        long[][] candidates = new long[n][2];
        int candCount = 0;
        long maxWorthPrice = 2L * minPrice;

        for (int[] item : items) {
            long factor = item[0];
            long price = item[1];
            long bonus = multCount[(int) factor];
            if (bonus > 0 && price <= maxWorthPrice) {
                candidates[candCount][0] = price;
                candidates[candCount][1] = bonus;
                candCount++;
            }
        }

        Arrays.sort(candidates, 0, candCount, Comparator.comparingLong(a -> a[0]));

        long remBudget = budget;
        long totalItems = 0;

        for (int i = 0; i < candCount; i++) {
            long price = candidates[i][0];
            long bonus = candidates[i][1];

            if (remBudget < price) {
                break;
            }

            long take = Math.min(bonus, remBudget / price);
            // Each taken copy gives 1 purchased + 1 free = 2 items
            totalItems += take * 2L;
            remBudget -= take * price;

            if (take < bonus) {
                break;
            }
        }

        // Use remaining budget to buy as many copies as possible of the cheapest item
        if (remBudget >= minPrice) {
            totalItems += remBudget / minPrice;
        }

        return (int) totalItems;
    }
}