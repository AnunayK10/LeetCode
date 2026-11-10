class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        // Iterate through all valid hours (0 to 11) and minutes (0 to 59)
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                // Check if the total number of set bits matches turnedOn
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    result.add(String.format("%d:%02d", h, m));
                }
            }
        }
        
        return result;
    }
}