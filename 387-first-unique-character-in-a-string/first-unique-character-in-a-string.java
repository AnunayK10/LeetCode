class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        
        // Count frequencies of all characters
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        // Find the first character with a frequency of 1
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        
        return -1;
    }
}