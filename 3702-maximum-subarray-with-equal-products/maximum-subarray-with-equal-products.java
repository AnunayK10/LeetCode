class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            long prod = nums[i];
            int gcd = nums[i];
            int lcm = nums[i];

            for (int j = i + 1; j < n; j++) {
                int val = nums[j];
                gcd = gcd(gcd, val);
                lcm = lcm(lcm, val);

                // Avoid overflow in prod * val by checking against max possible bound
                if (prod > Long.MAX_VALUE / val) {
                    break;
                }
                prod *= val;

                // Prune if product already exceeds maximum possible (lcm * gcd)
                if (prod > 2520L * gcd * lcm) {
                    break;
                }

                if (prod == (long) lcm * gcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}