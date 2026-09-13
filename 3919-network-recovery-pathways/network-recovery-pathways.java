class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new ArrayList[n];
        int[] inDegree = new int[n];
        int maxCost = 0;
        
        // 1. Build the graph using ONLY online nodes
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            
            // Both endpoints must be online to use this edge
            if (online[u] && online[v]) {
                if (adj[u] == null) {
                    adj[u] = new ArrayList<>();
                }
                adj[u].add(new int[]{v, cost});
                inDegree[v]++;
                if (cost > maxCost) {
                    maxCost = cost;
                }
            }
        }
        
        // 2. Kahn's algorithm to find Topological Sort Order
        int[] topo = new int[n];
        int topoCount = 0;
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (online[i] && inDegree[i] == 0) {
                q.add(i);
            }
        }
        
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[topoCount++] = u;
            
            if (adj[u] != null) {
                for (int[] edge : adj[u]) {
                    int v = edge[0];
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        q.add(v);
                    }
                }
            }
        }
        
        // Base case: If we can't reach node n-1 even without any minimum edge constraints
        if (!check(0, n, topo, topoCount, adj, k)) {
            return -1;
        }
        
        // 3. Binary Search for the maximum score
        int low = 0;
        int high = maxCost;
        int ans = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // If achievable, store it and try to find a higher minimum edge weight
            if (check(mid, n, topo, topoCount, adj, k)) {
                ans = mid;
                low = mid + 1; 
            } else {
                high = mid - 1; // Score is too high, lower the threshold
            }
        }
        
        return ans;
    }
    
    // Check if there's a valid path using edges with cost >= 'mid' and total cost <= 'k'
    private boolean check(int mid, int n, int[] topo, int topoCount, List<int[]>[] adj, long k) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0; 
        
        for (int i = 0; i < topoCount; i++) {
            int u = topo[i];
            
            // If the current node is unreachable, skip
            if (dp[u] == Long.MAX_VALUE) continue;
            
            if (adj[u] != null) {
                for (int[] edge : adj[u]) {
                    int v = edge[0];
                    int cost = edge[1];
                    
                    // Only traverse edges that meet our binary-searched minimum score
                    if (cost >= mid) {
                        dp[v] = Math.min(dp[v], dp[u] + cost);
                    }
                }
            }
        }
        
        // Check if destination is reachable and cost is within allowed limit
        return dp[n - 1] <= k;
    }
}