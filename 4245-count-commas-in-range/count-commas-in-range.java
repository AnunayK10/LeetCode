class Solution {
    public int countCommas(int n) {
        // Since the maximum constraint is n = 10^5 (100,000), 
        // the maximum number of commas any single number can have is 1.
        // Numbers less than 1000 have 0 commas.
        // Therefore, we just need to count how many numbers are >= 1000.
        
        return Math.max(0, n - 999);
    }
}