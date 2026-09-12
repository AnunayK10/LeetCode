class Solution {
    public int totalNumbers(int[] digits) {
        // Step 1: Count the frequencies of each digit in the input array
        int[] count = new int[10];
        for (int digit : digits) {
            count[digit]++;
        }
        
        int validCount = 0;
        
        // Step 2: Iterate through all possible 3-digit even numbers
        for (int i = 100; i < 1000; i += 2) {
            // Extract the digits
            int d1 = i / 100;          // hundreds place
            int d2 = (i / 10) % 10;    // tens place
            int d3 = i % 10;           // ones place
            
            // Temporarily use the digits
            count[d1]--;
            count[d2]--;
            count[d3]--;
            
            // Step 3: Check if we had enough of each digit to form this number
            if (count[d1] >= 0 && count[d2] >= 0 && count[d3] >= 0) {
                validCount++;
            }
            
            // Restore the counts for the next iteration
            count[d1]++;
            count[d2]++;
            count[d3]++;
        }
        
        return validCount;
    }
}