class Solution {
    public int largestAltitude(int[] gain) {
        int currentAltitude = 0;
        int maxAltitude = 0; // Starts at 0 because the initial altitude is 0
        
        for (int g : gain) {
            currentAltitude += g;
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        
        return maxAltitude;
    }
}