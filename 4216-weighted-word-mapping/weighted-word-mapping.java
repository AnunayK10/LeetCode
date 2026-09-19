class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            int weightSum = 0;
            
            // Calculate the total weight of the current word
            for (int i = 0; i < word.length(); i++) {
                weightSum += weights[word.charAt(i) - 'a'];
            }
            
            // Apply modulo 26 and map to the reverse alphabetical character
            int modValue = weightSum % 26;
            char mappedChar = (char) ('z' - modValue);
            
            // Append the mapped character to our result
            result.append(mappedChar);
        }
        
        return result.toString();
    }
}