class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int smallerCount = 0;
        int targetCount = 0;
        
        // Count numbers strictly smaller than target and numbers equal to target
        for (int num : nums) {
            if (num < target) {
                smallerCount++;
            } else if (num == target) {
                targetCount++;
            }
        }
        
        // Populate the list with the consecutive indices for the target
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < targetCount; i++) {
            result.add(smallerCount + i);
        }
        
        return result;
    }
}