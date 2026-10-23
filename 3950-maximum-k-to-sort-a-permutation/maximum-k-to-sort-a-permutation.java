class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int ans = -1; // Initialize to -1 to represent uninitialized state
        
        for (int i = 0; i < n; i++) {
            // If the element is not in its correct position
            if (nums[i] != i) {
                if (ans == -1) {
                    ans = nums[i];
                } else {
                    ans &= nums[i];
                }
            }
        }
        
        // If all elements are already in place, return 0
        return (ans == -1) ? 0 : ans;
    }
}