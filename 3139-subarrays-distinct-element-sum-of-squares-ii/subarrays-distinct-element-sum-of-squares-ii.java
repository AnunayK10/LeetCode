class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] sumTree;    // Stores sum of counts
    private long[] sqTree;     // Stores sum of squares of counts
    private long[] lazy;       // Lazy propagation array

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumTree = new long[4 * n];
        sqTree = new long[4 * n];
        lazy = new long[4 * n];

        HashMap<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int prev = lastSeen.getOrDefault(x, -1);
            
            // Update range [prev + 1, j] by adding 1
            update(1, 0, n - 1, prev + 1, j);
            lastSeen.put(x, j);
            
            // sqTree[1] gives the sum of squares of distinct counts for all subarrays ending at j
            totalSum = (totalSum + sqTree[1]) % MOD;
        }

        return (int) totalSum;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = r - l + 1;
        // When incrementing elements in range by 'val':
        // New sum of squares = old_sq + 2 * old_sum * val + len * val^2
        sqTree[node] = (sqTree[node] + 2 * sumTree[node] * val + len * val * val) % MOD;
        // New linear sum = old_sum + len * val
        sumTree[node] = (sumTree[node] + len * val) % MOD;
        // Accumulate lazy tag
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr);
        update(2 * node + 1, mid + 1, r, ql, qr);
        
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
        sqTree[node] = (sqTree[2 * node] + sqTree[2 * node + 1]) % MOD;
    }
}