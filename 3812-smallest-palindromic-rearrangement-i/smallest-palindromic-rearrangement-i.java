class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n / 2; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        char[] result = new char[n];
        int leftIndex = 0;
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                char ch = (char) ('a' + i);
                result[leftIndex] = ch;
                result[n - 1 - leftIndex] = ch;
                leftIndex++;
                count[i]--;
            }
        }
        if (n % 2 != 0) {
            result[n / 2] = s.charAt(n / 2);
        }       
        return new String(result);
    }
}