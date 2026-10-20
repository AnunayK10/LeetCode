class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        // 1. Precompute prefix maximums
        int[] prefMax = new int[n];
        prefMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i - 1], nums[i]);
        }

        // 2. Precompute suffix minimums
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }

        // 3. Precompute prefix drops (count of internal descents where nums[i-1] > nums[i])
        int[] prefDrops = new int[n];
        for (int i = 1; i < n; i++) {
            prefDrops[i] = prefDrops[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        // 4. Iterate over all divisors k of n
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                if (isValid(nums, prefMax, suffMin, prefDrops, k, n)) {
                    totalSum += k;
                }
                int k2 = n / k;
                if (k2 != k) {
                    if (isValid(nums, prefMax, suffMin, prefDrops, k2, n)) {
                        totalSum += k2;
                    }
                }
            }
        }

        return totalSum;
    }

    private boolean isValid(int[] nums, int[] prefMax, int[] suffMin, int[] prefDrops, int k, int n) {
        for (int i = 0; i < n; i += k) {
            int start = i;
            int end = i + k - 1;

            // Condition 1: Check block boundary ordering (Max of previous block <= Min of next block)
            if (start > 0 && prefMax[start - 1] > suffMin[start]) {
                return false;
            }

            // Condition 2: Check internal descents within the block
            int internalDrops = (end > start) ? (prefDrops[end] - prefDrops[start]) : 0;
            
            // A cyclic shift is possible if total descents (internal + circular wrap-around) <= 1
            int totalDrops = internalDrops + (nums[end] > nums[start] ? 1 : 0);
            if (totalDrops > 1) {
                return false;
            }
        }
        return true;
    }
}