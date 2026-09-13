class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // dist array stores the minimum damage taken to reach (i, j)
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        Deque<int[]> deque = new ArrayDeque<>();
        int startDamage = grid.get(0).get(0);
        dist[0][0] = startDamage;
        
        // Deque stores arrays of [row, col, current_damage]
        deque.offer(new int[]{0, 0, startDamage});
        
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];
            
            // If we reached the target, check if we survived
            if (r == m - 1 && c == n - 1) {
                return (health - d) >= 1;
            }
            
            // If we've already found a better path to this cell, skip
            if (d > dist[r][c]) continue;
            
            // Explore all 4 directions
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextDamage = d + grid.get(nr).get(nc);
                    
                    // If this path offers strictly less damage to reach (nr, nc)
                    if (nextDamage < dist[nr][nc]) {
                        dist[nr][nc] = nextDamage;
                        
                        // 0-weight edges go to the front, 1-weight edges go to the back
                        if (grid.get(nr).get(nc) == 0) {
                            deque.offerFirst(new int[]{nr, nc, nextDamage});
                        } else {
                            deque.offerLast(new int[]{nr, nc, nextDamage});
                        }
                    }
                }
            }
        }
        
        return false;
    }
}