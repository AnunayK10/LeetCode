class Solution {
    public int arrangeCoins(int n) {
        long left = 0, right = n;
        long ans = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long coinsNeeded = mid * (mid + 1) / 2;

            if (coinsNeeded <= n) {
                ans = mid;
                left = mid + 1; // Try to fit more rows
            } else {
                right = mid - 1; // Too many coins, reduce rows
            }
        }

        return (int) ans;
    }
}