class Solution {
    public int numberOfSubstrings(String s) {
        // To store the last seen index of 'a', 'b', and 'c'
        int[] lastSeen = {-1, -1, -1};
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Update the most recent index of the current character
            lastSeen[s.charAt(i) - 'a'] = i;
            
            // Find the minimum index among the last seen positions of 'a', 'b', and 'c'
            int minIndex = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            
            // If all three characters have appeared at least once, minIndex will be >= 0
            // Any substring starting from index 0 up to minIndex and ending at i is valid
            if (minIndex != -1) {
                count += (minIndex + 1);
            }
        }
        
        return count;
    }
}