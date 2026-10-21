class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        
        // Case 0: Already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        // If length is 2 and not sorted, impossible to apply a proper substring operation
        if (n == 2) return -1;
        
        // Find min and max characters and their positions
        char minChar = arr[0], maxChar = arr[0];
        for (char c : arr) {
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        // Check if minChar is at the beginning or maxChar is at the end
        if (arr[0] == minChar || arr[n - 1] == maxChar) {
            return 1;
        }
        
        // Check if single max is at index 0 and single min is at index n - 1
        boolean uniqueMaxAtStart = (arr[0] == maxChar);
        for (int i = 1; i < n; i++) {
            if (arr[i] == maxChar) {
                uniqueMaxAtStart = false;
                break;
            }
        }
        
        boolean uniqueMinAtEnd = (arr[n - 1] == minChar);
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == minChar) {
                uniqueMinAtEnd = false;
                break;
            }
        }
        
        if (uniqueMaxAtStart && uniqueMinAtEnd) {
            return 3;
        }
        
        // Default fallback for other transformable cases
        return 2;
    }
}