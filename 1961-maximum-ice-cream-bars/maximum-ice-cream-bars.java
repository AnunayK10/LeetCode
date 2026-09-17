class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Step 1: Find the maximum cost
        int maxCost = 0;
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }
        
        // Step 2: Create a frequency map (Counting Sort)
        int[] count = new int[maxCost + 1];
        for (int cost : costs) {
            count[cost]++;
        }
        
        int iceCreams = 0;
        
        // Step 3: Greedily pick the cheapest ice creams first
        for (int price = 1; price <= maxCost; price++) {
            if (count[price] == 0) {
                continue;
            }
            
            // If the current price is strictly greater than our remaining coins, we are done
            if (coins < price) {
                break;
            }
            
            // Calculate how many ice creams we can afford at this price
            int affordableCount = Math.min(count[price], coins / price);
            
            // Deduct the cost and add to our total ice cream count
            coins -= affordableCount * price;
            iceCreams += affordableCount;
        }
        
        return iceCreams;
    }
}