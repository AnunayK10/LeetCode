class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128]; // ASCII size to cover lowercase and uppercase letters
        
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        
        int length = 0;
        boolean hasOdd = false;
        
        for (int freq : count) {
            length += (freq / 2) * 2; // Add the maximum even pairs
            if (freq % 2 == 1) {
                hasOdd = true;
            }
        }
        
        // If there's any character with an odd frequency, we can place one in the middle
        if (hasOdd) {
            length += 1;
        }
        
        return length;
    }
}