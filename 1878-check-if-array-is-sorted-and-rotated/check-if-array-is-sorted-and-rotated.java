class Solution {
    public boolean check(int[] nums) {
        int drops = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // Compare the current element with the next element (using modulo for wrap-around)
            if (nums[i] > nums[(i + 1) % n]) {
                drops++;
            }
            
            // If there's more than one drop, it's not a sorted and rotated array
            if (drops > 1) {
                return false;
            }
        }
        
        return true;
    }
}