class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort to group duplicates together
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // If already used, skip
            if (used[i]) continue;
            
            // Skip duplicates: if the current element is equal to the previous one,
            // and the previous one was NOT used in this branch, skip to avoid duplicate permutations.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            current.add(nums[i]);
            
            backtrack(nums, used, current, result);
            
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}