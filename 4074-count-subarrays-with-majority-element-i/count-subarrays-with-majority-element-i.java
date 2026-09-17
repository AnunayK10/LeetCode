class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // Prefix sum can range from -n to n. 
        // We use an array of size 2n + 1 and offset by 'n' to avoid negative indices.
        int[] freq = new int[2 * n + 1];
        int offset = n;
        
        int prefix = 0;
        freq[prefix + offset] = 1; // Base case: prefix sum of 0 at the start
        
        int countLess = 0;
        int totalSubarrays = 0;
        
        for (int num : nums) {
            if (num == target) {
                // Prefix sum increases by 1.
                // Elements strictly less than the new prefix sum include 
                // all previously counted elements PLUS the frequencies of the old prefix sum.
                countLess += freq[prefix + offset];
                prefix++;
            } else {
                // Prefix sum decreases by 1.
                // We subtract the frequencies of the new prefix sum from our 'less than' count.
                prefix--;
                countLess -= freq[prefix + offset];
            }
            
            // Add valid subarrays ending at the current index
            totalSubarrays += countLess;
            
            // Record the occurrence of the current prefix sum
            freq[prefix + offset]++;
        }
        
        return totalSubarrays;
    }
}