class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort to enable early stopping/pruning
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> current, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early pruning: since array is sorted, subsequent elements will also exceed remain
            if (candidates[i] > remain) {
                break;
            }

            current.add(candidates[i]);
            // Stay at index 'i' to allow choosing the same element multiple times
            backtrack(candidates, remain - candidates[i], i, current, result);
            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}