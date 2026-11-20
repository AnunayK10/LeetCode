class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        // dp.get(v) stores the longest consecutive sequence ending at value v
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 1;

        for (int x : nums) {
            // Option 1: use x as (x + 1), extending a sequence ending at x
            int lenPlusOne = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, lenPlusOne);
            if (lenPlusOne > maxLen) {
                maxLen = lenPlusOne;
            }

            // Option 2: keep x as x, extending a sequence ending at (x - 1)
            int lenSame = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, lenSame);
            if (lenSame > maxLen) {
                maxLen = lenSame;
            }
        }

        return maxLen;
    }
}