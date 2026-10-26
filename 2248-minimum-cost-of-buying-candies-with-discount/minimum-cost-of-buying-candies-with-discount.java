class Solution {
    public int minimumCost(int[] cost) {
        // Sort the array in ascending order first
        Arrays.sort(cost);
        
        int totalCost = 0;
        int n = cost.length;
        
        // Traverse from the most expensive candy to the least expensive
        for (int i = n - 1; i >= 0; i--) {
            // If this is the 3rd candy in a group of 3 from the right, it's free
            // Alternatively, we can use a counter: every 3rd candy we skip.
            // Let's use a 1-based index from the end:
            int idxFromEnd = n - i;
            if (idxFromEnd % 3 == 0) {
                continue; // Skip this candy (it's free!)
            }
            totalCost += cost[i];
        }
        
        return totalCost;
    }
}