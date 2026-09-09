class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // If the array has fewer than 4 elements, return empty list
        if (nums == null || nums.length < 4) {
            return result;
        }

        // Sort the array to easily skip duplicates and use two pointers
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for the second element
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Use long to prevent integer overflow since values can be up to 10^9
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Skip duplicates for the third element
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // Skip duplicates for the fourth element
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        
                        // Move both pointers inward after finding a valid quadruplet
                        left++;
                        right--;
                    } else if (sum < target) {
                        // If the sum is too small, move the left pointer to the right to increase the sum
                        left++;
                    } else {
                        // If the sum is too large, move the right pointer to the left to decrease the sum
                        right--;
                    }
                }
            }
        }

        return result;
    }
}