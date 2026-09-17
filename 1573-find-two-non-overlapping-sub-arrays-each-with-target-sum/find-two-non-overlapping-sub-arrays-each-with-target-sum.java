class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // Stores the minimum length of a valid sub-array ending at or before index i
        int[] minLengths = new int[n]; 
        
        int left = 0;
        int sum = 0;
        int minLenSoFar = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the window if the sum exceeds the target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            // When a valid sub-array is found
            if (sum == target) {
                int currentLen = right - left + 1;
                
                // Check if there is a non-overlapping valid sub-array before 'left'
                if (left > 0 && minLengths[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, minLengths[left - 1] + currentLen);
                }
                
                // Update the minimum length found so far
                minLenSoFar = Math.min(minLenSoFar, currentLen);
            }
            
            // Store the best minimum length up to the current right index
            minLengths[right] = minLenSoFar;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}