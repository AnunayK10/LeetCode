public class Solution {
    public int maximumLengthSubstring(String s) {
        int maxLen = 0;
        int left = 0;
        // Since s contains only lowercase English letters, a size-26 array is optimal
        int[] counts = new int[26];

        for (int right = 0; right < s.length(); right++) {
            int rightCharIdx = s.charAt(right) - 'a';
            counts[rightCharIdx]++;

            // Shrink the window from the left if any character count exceeds 2
            while (counts[rightCharIdx] > 2) {
                int leftCharIdx = s.charAt(left) - 'a';
                counts[leftCharIdx]--;
                left++; // Slide the left boundary forward
            }

            // Capture the maximum valid substring size found so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
