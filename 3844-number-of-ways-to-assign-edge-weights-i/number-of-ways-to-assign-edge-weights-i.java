class Solution {
    private static final int MOD = 1000000007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Find maximum depth using BFS or DFS
        int maxDepth = 0;
        Queue<int[]> queue = new LinkedList<>(); // stores {node, depth}
        boolean[] visited = new boolean[n + 1];

        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int depth = curr[1];

            maxDepth = Math.max(maxDepth, depth);

            for (int neighbor : graph.get(u)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, depth + 1});
                }
            }
        }

        // The number of ways is 2^(maxDepth - 1) % MOD
        if (maxDepth == 0) return 0;
        return (int) power(2, maxDepth - 1, MOD);
    }

    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}