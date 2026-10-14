class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Validation: sum must be within the possible range for 'num' digits
        if (sum < 1 || sum > 9 * (long) num) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num; i++) {
            // Try digits from 9 down to 0
            for (int d = 9; d >= 0; d--) {
                // Check if choosing digit 'd' leaves a valid remaining sum
                long remainingSum = sum - d;
                long remainingDigits = num - 1 - i;
                
                if (remainingSum >= 0 && remainingSum <= 9 * remainingDigits) {
                    sb.append(d);
                    sum -= d;
                    break;
                }
            }
        }

        return sb.toString();
    }
}
