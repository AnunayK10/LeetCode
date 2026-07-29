public class Solution {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }
        int[][] safeness = new int[n][n];
        for (int[] row : safeness) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> bfsQueue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    bfsQueue.offer(new int[]{r, c});
                    safeness[r][c] = 0;
                }
            }
        }
        while (!bfsQueue.isEmpty()) {
            int[] curr = bfsQueue.poll();
            int r = curr[0], c = curr[1];
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safeness[nr][nc] == -1) {
                    safeness[nr][nc] = safeness[r][c] + 1;
                    bfsQueue.offer(new int[]{nr, nc});
                }
            }
        }
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        boolean[][] visited = new boolean[n][n];
        maxHeap.offer(new int[]{safeness[0][0], 0, 0});
        visited[0][0] = true;
        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int currentSafeness = curr[0];
            int r = curr[1], c = curr[2];
            if (r == n - 1 && c == n - 1) {
                return currentSafeness;
            }
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    int nextSafeness = Math.min(currentSafeness, safeness[nr][nc]);
                    maxHeap.offer(new int[]{nextSafeness, nr, nc});
                }
            }
        }
        return 0;
    }
}
