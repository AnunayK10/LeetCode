public class Solution {
    public int[] validSequence(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        
        // last[j] stores the maximum index in word1 that matches word2[j]
        int[] last = new int[n2];
        Arrays.fill(last, -1);
        
        // 1. Right-to-Left Pre-computation to map the matching suffix boundaries
        int i = n1 - 1;
        int j = n2 - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }
        
        int[] ans = new int[n2];
        boolean canSkip = true; // Tracks if the 1-character modification is still available
        
        // 2. Left-to-Right Greedy Sequence Matching
        j = 0;
        for (i = 0; i < n1; i++) {
            if (j == n2) {
                break; // Fully matched word2
            }
            
            // Scenario A: Exact character match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } 
            // Scenario B: Mismatch - evaluate if we can greedily burn our "one change" rule here
            else if (canSkip && (j + 1 == n2 || last[j + 1] > i)) {
                ans[j] = i; // Accept the mismatch greedily at this early index
                canSkip = false; // Burn the edit privilege
                j++;
            }
        }
        
        // If we successfully found a match for all characters in word2
        return (j == n2) ? ans : new int[0];
    }
}
