class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        // Base case: if array has 1 or 0 elements, 0 jumps needed
        if (n <= 1) return 0;
        
        // Map to store indices of each value to quickly find j where arr[i] == arr[j]
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        
        // Queue for BFS storing indices
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        // Visited array to prevent cycles
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all nodes at the current BFS level
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                // If we've reached the last index, return the step count
                if (curr == n - 1) {
                    return steps;
                }
                
                // 1. Check jump to i - 1
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    queue.offer(curr - 1);
                }
                
                // 2. Check jump to i + 1
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    queue.offer(curr + 1);
                }
                
                // 3. Check jumps to j where arr[curr] == arr[j]
                if (graph.containsKey(arr[curr])) {
                    for (int next : graph.get(arr[curr])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    // CRUCIAL OPTIMIZATION: Clear the list for this value after visiting 
                    // to prevent redundant operations and Time Limit Exceeded (TLE).
                    graph.remove(arr[curr]);
                }
            }
            steps++; // Increment steps after processing the current level
        }
        
        return -1;
    }
}