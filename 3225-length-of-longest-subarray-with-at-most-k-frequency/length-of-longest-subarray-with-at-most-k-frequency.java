public class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int maxLen = 0;
        int left = 0;
        // Frequency map to store the occurrence count of each integer in the current window
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            int currentNum = nums[right];
            // Increment frequency for the incoming right element
            frequencyMap.put(currentNum, frequencyMap.getOrDefault(currentNum, 0) + 1);

            // Shrink the window from the left if the current number's frequency exceeds k
            while (frequencyMap.get(currentNum) > k) {
                int leftNum = nums[left];
                frequencyMap.put(leftNum, frequencyMap.get(leftNum) - 1);
                left++; // Slide the left boundary forward
            }

            // Capture the maximum valid window size seen so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
