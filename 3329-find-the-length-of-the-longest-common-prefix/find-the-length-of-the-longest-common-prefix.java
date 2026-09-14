class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> prefixes = new HashSet<>();
        
        // Step 1: Add all possible prefixes of elements in arr1 to the HashSet
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }
        
        int maxLength = 0;
        
        // Step 2: Check prefixes of elements in arr2 against the HashSet
        for (int num : arr2) {
            while (num > 0) {
                // If a matching prefix is found
                if (prefixes.contains(num)) {
                    // Calculate the number of digits in this prefix
                    int len = Integer.toString(num).length();
                    maxLength = Math.max(maxLength, len);
                    break; // Break early because we are checking longest prefixes first
                }
                // Reduce the number to check the next shorter prefix
                num /= 10;
            }
        }
        
        return maxLength;
    }
}