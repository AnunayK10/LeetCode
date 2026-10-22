class Solution {
    public int minOperations(int[] nums) {
        int length = nums.length;

        // Locate the index of the element whose value is 0,
        // which serves as the starting anchor point.
        int zeroIndex = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        // Effectively final copy needed for use inside the lambda.
        final int anchorIndex = zeroIndex;

        // Predicate that verifies whether, starting from the anchor and
        // walking in the given direction (step), the array is non-decreasing.
        // The traversal wraps around using modular arithmetic so the array
        // is treated as circular.
        IntPredicate isNonDecreasing = step -> {
            for (int i = 1; i < length; i++) {
                // Index of the previous element along the chosen direction.
                int prevIndex = (anchorIndex + (i - 1) * step + length) % length;
                // Index of the current element along the chosen direction.
                int currIndex = (anchorIndex + i * step + length) % length;

                // If the order breaks, the sequence is not sorted in this direction.
                if (nums[prevIndex] > nums[currIndex]) {
                    return false;
                }
            }

            return true;
        };

        int answer = Integer.MAX_VALUE;

        // Check the forward (step = +1) direction.
        if (isNonDecreasing.test(1)) {
            // Cost of shifting so the anchor reaches its target one way.
            answer = Math.min(answer, zeroIndex);
            // Cost of shifting in the complementary direction.
            answer = Math.min(answer, length - zeroIndex + 2);
        }

        // Check the backward (step = -1) direction.
        if (isNonDecreasing.test(-1)) {
            // Cost of shifting so the anchor reaches its target one way.
            answer = Math.min(answer, zeroIndex + 2);
            // Cost of shifting in the complementary direction.
            answer = Math.min(answer, length - zeroIndex);
        }

        // If no valid arrangement was found, return -1.
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
