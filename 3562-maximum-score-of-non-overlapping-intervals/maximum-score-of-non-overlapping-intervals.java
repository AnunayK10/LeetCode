class Solution {
    // Wrapper class to store interval bounds, weight, and original index
    static class Interval {
        int l, r;
        long w;
        int id;
        
        Interval(int l, int r, long w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] sortedIntervals = new Interval[n];
        
        // Extract intervals and assign original indices
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sortedIntervals[i] = new Interval(interval.get(0), interval.get(1), (long) interval.get(2), i);
        }

        // Sort by right endpoint. If there's a tie, sort by left endpoint
        Arrays.sort(sortedIntervals, (a, b) -> {
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            return Integer.compare(a.l, b.l);
        });

        // DP tables to store the max scores and the respective indices taken
        long[][] dp_score = new long[n + 1][5];
        int[][][] dp_ids = new int[n + 1][5][];

        // Initialize base cases for 0 items
        for (int k = 0; k <= 4; k++) {
            dp_ids[0][k] = new int[0];
        }

        for (int i = 1; i <= n; i++) {
            Interval current = sortedIntervals[i - 1];

            // Binary search to find the latest interval that does NOT overlap
            // We need sortedIntervals[mid].r < current.l
            int low = 0, high = i - 2;
            int best_j = -1;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (sortedIntervals[mid].r < current.l) {
                    best_j = mid;
                    low = mid + 1; // Try to find a later one
                } else {
                    high = mid - 1;
                }
            }
            int j = best_j + 1; // 1-based index matching the DP table

            dp_score[i][0] = 0;
            dp_ids[i][0] = new int[0];

            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip the current interval
                long scoreSkip = dp_score[i - 1][k];
                int[] idsSkip = dp_ids[i - 1][k];

                // Option 2: Take the current interval
                long scoreTake = dp_score[j][k - 1] + current.w;
                int[] baseIds = dp_ids[j][k - 1];
                
                int[] idsTake = new int[baseIds.length + 1];
                System.arraycopy(baseIds, 0, idsTake, 0, baseIds.length);
                idsTake[baseIds.length] = current.id;
                Arrays.sort(idsTake); // Keep the chosen ids sorted

                // Determine the winner
                if (isBetter(scoreTake, idsTake, scoreSkip, idsSkip)) {
                    dp_score[i][k] = scoreTake;
                    dp_ids[i][k] = idsTake;
                } else {
                    dp_score[i][k] = scoreSkip;
                    dp_ids[i][k] = idsSkip;
                }

                // Propagate a state with fewer elements if it yields the same or better score
                // (This intrinsically handles the "up to 4" part of the constraint cleanly)
                if (isBetter(dp_score[i][k - 1], dp_ids[i][k - 1], dp_score[i][k], dp_ids[i][k])) {
                    dp_score[i][k] = dp_score[i][k - 1];
                    dp_ids[i][k] = dp_ids[i][k - 1];
                }
            }
        }

        // Return the sequence of indices leading to the best outcome out of up to 4 items
        return dp_ids[n][4];
    }

    // Evaluates True if Candidate A strictly out-prioritizes Candidate B
    private boolean isBetter(long scoreA, int[] idsA, long scoreB, int[] idsB) {
        if (scoreA > scoreB) return true;
        if (scoreA < scoreB) return false;
        
        // If scores are tied, the lexicographically smallest array of indices wins
        for (int i = 0; i < Math.min(idsA.length, idsB.length); i++) {
            if (idsA[i] < idsB[i]) return true;
            if (idsA[i] > idsB[i]) return false;
        }
        
        // If prefixes match, the shorter array is lexicographically smaller 
        return idsA.length < idsB.length;
    }
}