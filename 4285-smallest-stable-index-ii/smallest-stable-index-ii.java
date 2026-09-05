class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Precompute the minimum values from the right (suffix minimums)
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }
        
        // Calculate prefix maximum on the fly and find the smallest stable index
        int prefMax = nums[0];
        for (int i = 0; i < n; i++) {
            prefMax = Math.max(prefMax, nums[i]);
            if (prefMax - suffMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}