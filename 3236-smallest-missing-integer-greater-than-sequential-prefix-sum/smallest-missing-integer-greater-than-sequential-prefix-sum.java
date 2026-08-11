public class Solution {
    public int missingInteger(int[] nums) {
        // 1. Calculate the sum of the longest sequential prefix
        int prefixSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break; // Sequence broken, exit loop
            }
        }

        // 2. Add all numbers to a HashSet for O(1) membership testing
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // 3. Find the smallest missing integer starting from the prefixSum
        while (numSet.contains(prefixSum)) {
            prefixSum++;
        }

        return prefixSum;
    }
}
