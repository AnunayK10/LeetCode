public class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean hasNonZero = false;

        // Compute total XOR of all elements and check for any non-zero value
        for (int num : nums) {
            xor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // Case 1: The total XOR of all elements is already non-zero
        if (xor != 0) {
            return nums.length;
        }

        // Case 2: Total XOR is 0, but if there is at least one non-zero element, 
        // we can exclude it to make the remaining subsequence XOR non-zero.
        if (hasNonZero) {
            return nums.length - 1;
        }

        // Case 3: All elements in the array are 0
        return 0;
    }
}
