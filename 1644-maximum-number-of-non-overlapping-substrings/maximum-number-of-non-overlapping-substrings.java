class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int[] l = new int[26];
        int[] r = new int[26];
        Arrays.fill(l, -1);
        
        int n = s.length();
        // Step 1: Find the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (l[c] == -1) {
                l[c] = i;
            }
            r[c] = i;
        }
        
        // Step 2: Find all valid intervals for substrings
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (l[i] != -1) {
                int right = getRight(l[i], l, r, s);
                // If the interval doesn't force us to expand to the left of l[i], it's valid
                if (right != -1) {
                    intervals.add(new int[]{l[i], right});
                }
            }
        }
        
        // Step 3: Sort valid intervals by their end index to greedily pick the maximum non-overlapping substrings
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> res = new ArrayList<>();
        int prev = -1;
        // Step 4: Pick non-overlapping intervals
        for (int[] interval : intervals) {
            if (interval[0] > prev) {
                res.add(s.substring(interval[0], interval[1] + 1));
                prev = interval[1];
            }
        }
        
        return res;
    }
    
    // Helper method to determine the right boundary of a valid substring starting at index i
    private int getRight(int i, int[] l, int[] r, String s) {
        int right = r[s.charAt(i) - 'a'];
        for (int j = i; j <= right; j++) {
            // If a character inside our current window starts before our window's start, 
            // a valid substring starting at 'i' is impossible without expanding left.
            if (l[s.charAt(j) - 'a'] < i) {
                return -1;
            }
            // Expand the right boundary if necessary
            right = Math.max(right, r[s.charAt(j) - 'a']);
        }
        return right;
    }
}