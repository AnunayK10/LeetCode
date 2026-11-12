class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // We don't need to jump from the last index (n - 1)
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            // If we have reached the end of the current jump's range, we must make another jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                
                // Early exit if we can already reach or exceed the last index
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}