class Solution {
    private static final long BASE1 = 1315423911L;
    private static final long MOD1 = 1000000007L;
    private static final long BASE2 = 133333331L;
    private static final long MOD2 = 1000000009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid length
            } else {
                low = mid + 1;  // Need a larger length
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        // Compute BASE^(len - 1) % MOD for removing the leftmost element
        for (int i = 0; i < len - 1; i++) {
            p1 = (p1 * BASE1) % MOD1;
            p2 = (p2 * BASE2) % MOD2;
        }

        // Compute initial hash for the first window [0 ... len - 1]
        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        // Combine both 32-bit hashes into a single 64-bit key
        Map<Long, Integer> freq = new HashMap<>(n - len + 1);
        long initialKey = (h1 << 32) | h2;
        freq.put(initialKey, 1);

        // Slide the window across the rest of the array
        for (int i = len; i < n; i++) {
            int out = nums[i - len];
            int in = nums[i];

            // Remove outgoing element and add incoming element
            h1 = (h1 - (out * p1) % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + in) % MOD1;

            h2 = (h2 - (out * p2) % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + in) % MOD2;

            long key = (h1 << 32) | h2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        // Check if any subarray hash appeared exactly once
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}