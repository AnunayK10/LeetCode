class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroIdx = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Swap only when indices differ to save unnecessary writes
                if (i != nonZeroIdx) {
                    int temp = nums[nonZeroIdx];
                    nums[nonZeroIdx] = nums[i];
                    nums[i] = temp;
                }
                nonZeroIdx++;
            }
        }
    }
}