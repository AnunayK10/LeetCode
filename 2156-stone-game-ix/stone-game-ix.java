public class Solution {
    public boolean stoneGameIX(int[] stones) {
        // 1. Classify stones based on their remainders when divided by 3
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }

        // 2. Apply game theory rules based on the parity of turn-skipping '0' stones
        if (count[0] % 2 == 0) {
            // Even type 0s: Alice wins if both remainder types 1 and 2 are present
            return Math.min(count[1], count[2]) > 0;
        }

        // Odd type 0s: Alice wins if the gap between type 1 and type 2 stones is greater than 2
        return Math.abs(count[1] - count[2]) > 2;
    }
}
