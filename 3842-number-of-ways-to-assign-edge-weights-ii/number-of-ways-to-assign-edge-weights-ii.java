class Solution {
    private int[] depth;
    private int[][] up;
    private int LOG;
    
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        
        // Calculate the maximum logarithm needed for binary lifting
        LOG = 0;
        while ((1 << LOG) <= n) {
            LOG++;
        }
        
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        depth = new int[n + 1];
        up = new int[n + 1][LOG];
        
        // Precompute depths and binary lifting array
        dfs(1, 1, adj); 
        
        // Precompute powers of 2 modulo 10^9 + 7
        int MOD = 1_000_000_007;
        int[] pow2 = new int[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = (int) ((pow2[i - 1] * 2L) % MOD);
        }
        
        // Answer all queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            int lcaNode = getLca(u, v);
            // Distance = depth[u] + depth[v] - 2 * depth[LCA(u, v)]
            int dist = depth[u] + depth[v] - 2 * depth[lcaNode];
            
            if (dist == 0) {
                ans[i] = 0;
            } else {
                ans[i] = pow2[dist - 1];
            }
        }
        
        return ans;
    }
    
    private void dfs(int u, int parent, List<List<Integer>> adj) {
        up[u][0] = parent;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        
        for (int v : adj.get(u)) {
            if (v != parent) {
                depth[v] = depth[u] + 1;
                dfs(v, u, adj);
            }
        }
    }
    
    private int getLca(int u, int v) {
        // Ensure u is deeper than v
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        // Align u and v to the same depth
        int diff = depth[u] - depth[v];
        for (int i = 0; i < LOG; i++) {
            if (((diff >> i) & 1) == 1) {
                u = up[u][i];
            }
        }
        
        if (u == v) {
            return u;
        }
        
        // Move both pointers upwards together
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        
        return up[u][0]; // Return the parent of the matched nodes
    }
}