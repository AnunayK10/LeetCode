class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If the current index is beyond our maximum reach, we can't proceed further
            if (i > maxReach) {
                return false;
            }
            // Update the furthest index we can reach so far
            maxReach = Math.max(maxReach, i + nums[i]);
            
            // Early exit if we can already reach or exceed the last index
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
}