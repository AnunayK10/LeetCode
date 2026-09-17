class Solution {
    public int maxNumberOfBalloons(String text) {
        // Array to store the frequency of each lowercase letter
        int[] counts = new int[26];
        
        // Count the frequencies in the given text
        for (char c : text.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Find the maximum number of times "balloon" can be formed
        int minBalloons = counts['b' - 'a'];
        minBalloons = Math.min(minBalloons, counts['a' - 'a']);
        minBalloons = Math.min(minBalloons, counts['l' - 'a'] / 2); // 'l' is needed twice
        minBalloons = Math.min(minBalloons, counts['o' - 'a'] / 2); // 'o' is needed twice
        minBalloons = Math.min(minBalloons, counts['n' - 'a']);
        
        return minBalloons;
    }
}