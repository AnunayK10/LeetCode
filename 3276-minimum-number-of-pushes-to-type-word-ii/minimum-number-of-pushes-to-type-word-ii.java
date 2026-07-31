public class Solution {
    public int minimumPushes(String word) {
        int[] frequencies = new int[26];
        for (int i = 0; i < word.length(); i++) {
            frequencies[word.charAt(i) - 'a']++;
        }        
        Arrays.sort(frequencies);
        int totalPushes = 0;
        int distinctCharCount = 0;
        for (int i = 25; i >= 0; i--) {
            if (frequencies[i] == 0) {
                break;
            }
            int pressMultiplier = (distinctCharCount / 8) + 1;   
            totalPushes += frequencies[i] * pressMultiplier;
            distinctCharCount++;
        }        
        return totalPushes;
    }
}
