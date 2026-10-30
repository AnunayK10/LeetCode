class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, w]
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj[u].add(new int[] { v, w });
        }

        // Priority Queue stores: [current_weight_sum, node, consecutive_count]
        // Ordered by weight sum ascending
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Distance table: dist[node][count] stores the minimum weight to reach (node, count)
        // count ranges from 1 to k, so we size it as [n][k + 1]
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Start from node 0 with count 1 and weight 0
        pq.offer(new long[] { 0, 0, 1 });
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            // If we reached the destination node n - 1, return the total weight
            if (u == n - 1) {
                return (int) d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            // Explore neighbors
            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // Prune if consecutive identical characters exceed k
                if (nextCount > k) {
                    continue;
                }

                if (d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new long[] { d + w, v, nextCount });
                }
            }
        }

        // If no valid path to node n - 1 is found
        return -1;
    }
}