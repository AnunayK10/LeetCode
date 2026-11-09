class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        
        int[] charCount = new int[26];
        
        // Count each character in the magazine
        for (char c : magazine.toCharArray()) {
            charCount[c - 'a']++;
        }
        
        // Subtract counts using characters from the ransom note
        for (char c : ransomNote.toCharArray()) {
            charCount[c - 'a']--;
            if (charCount[c - 'a'] < 0) {
                return false;
            }
        }
        
        return true;
    }
}