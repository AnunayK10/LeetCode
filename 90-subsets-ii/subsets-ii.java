class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array to easily identify and skip duplicates
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, int start) {
        // Add the current subset (deep copy) to the result list
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicates to avoid duplicate subsets
            // Only skip if it's not the first element in the current recursive level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Include the current element
            current.add(nums[i]);

            // Recurse with the next index
            backtrack(result, current, nums, i + 1);

            // Backtrack: remove the element to explore other subsets
            current.remove(current.size() - 1);
        }
    }
}