class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        
        // Find the global maximum and minimum elements in the array
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            minVal = Math.min(minVal, num);
        }
        
        // Multiply the maximum possible single subarray value by k
        return 1L * (maxVal - minVal) * k;
    }
}