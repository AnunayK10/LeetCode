class Solution {
    public int maximumLength(int[] nums) {
        // Use Long to prevent overflow when squaring large numbers
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }
        
        int maxLen = 1;
        
        // Handle the special case where the base is 1
        if (freq.containsKey(1L)) {
            int countOnes = freq.get(1L);
            // The sequence of 1s must be odd in length
            maxLen = Math.max(maxLen, countOnes % 2 == 1 ? countOnes : countOnes - 1);
        }
        
        // Check sequences starting from every other unique number
        for (long num : freq.keySet()) {
            if (num == 1) continue; // 1 is already handled
            
            long curr = num;
            int currentLen = 0;
            
            // As long as we have pairs, we can build the left and right sides of the pattern
            while (freq.getOrDefault(curr, 0) > 1) {
                currentLen += 2;
                curr = curr * curr;
            }
            
            // If we have exactly one of the next square, it can be the peak
            if (freq.getOrDefault(curr, 0) == 1) {
                currentLen += 1;
            } else {
                // If we don't have the next square, the last pair we counted must act as the peak
                // effectively reducing our pair count by 1 to make a single peak
                currentLen -= 1; 
            }
            
            maxLen = Math.max(maxLen, currentLen);
        }
        
        return maxLen;
    }
}