public class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToSeats = new HashMap<>();
        
        // 1. Map each reserved seat into its respective row's bitmask
        for (int[] r : reservedSeats) {
            int row = r[0];
            int seat = r[1];
            rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << (seat - 1)));
        }
        
        int ans = 0;
        
        // 2. Evaluate rows that contain at least one reservation
        for (int seats : rowToSeats.values()) {
            boolean leftAssigned = false;
            
            // Check left block (seats 2, 3, 4, 5) -> binary representation check
            if ((seats & 0b0111100000) == 0) {
                ans++;
                leftAssigned = true;
            }
            
            // Check right block (seats 6, 7, 8, 9)
            if ((seats & 0b0000011110) == 0) {
                ans++;
            } 
            // If right block is blocked but the middle block (seats 4, 5, 6, 7) is open (and left wasn't taken)
            else if (!leftAssigned && (seats & 0b0001111000) == 0) {
                ans++;
            }
        }
        
        // 3. Add 2 groups for every completely unreserved row
        ans += (n - rowToSeats.size()) * 2;
        
        return ans;
    }
}
