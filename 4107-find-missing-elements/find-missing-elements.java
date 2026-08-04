public class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] present = new boolean[102];
        int mn = 101;
        int mx = 0;
        for (int x : nums) {
            if (x < mn) mn = x;
            if (x > mx) mx = x;
            present[x] = true;
        }
        List<Integer> missing = new ArrayList<>();
        for (int i = mn + 1; i < mx; i++) {
            if (!present[i]) {
                missing.add(i);
            }
        }
        return missing;
    }
}
