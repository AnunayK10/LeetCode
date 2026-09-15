class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // Shift by 'n' to handle negative indices since prefix sums range from -n to +n
        int[] count = new int[2 * n + 1];
        
        int currentPrefix = 0;
        count[n] = 1; // Base case: prefix sum 0 has frequency 1 at the start
        
        long currentLess = 0; // Tracks the number of prefixes strictly less than currentPrefix
        long ans = 0;
        
        for (int num : nums) {
            if (num == target) {
                // Moving up: the new strictly smaller prefixes are those that were equal to currentPrefix
                currentLess += count[currentPrefix + n];
                currentPrefix++;
            } else {
                // Moving down: currentPrefix drops, so we must remove the ones that are now equal to our new currentPrefix
                currentPrefix--;
                currentLess -= count[currentPrefix + n];
            }
            
            ans += currentLess;
            count[currentPrefix + n]++;
        }
        
        return ans;
    }
}