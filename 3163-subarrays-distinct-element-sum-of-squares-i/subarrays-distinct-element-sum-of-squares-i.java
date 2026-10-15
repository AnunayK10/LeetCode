class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Iterate over all possible starting points
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            // Iterate over all possible ending points for the current starting point
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                int distinctCount = distinctElements.size();
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}