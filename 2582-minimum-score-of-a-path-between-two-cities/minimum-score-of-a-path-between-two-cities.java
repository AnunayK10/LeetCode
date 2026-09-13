class Solution {
    int[] parent;
    
    // Find the root of the component with path compression
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }
    
    // Union two components
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }
    
    public int minScore(int n, int[][] roads) {
        // Initialize parent array for 1 to n cities
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        // Group all connected cities
        for (int[] road : roads) {
            union(road[0], road[1]);
        }
        
        int minScore = Integer.MAX_VALUE;
        int rootOf1 = find(1);
        
        // Find the minimum edge weight in the component containing City 1
        for (int[] road : roads) {
            if (find(road[0]) == rootOf1) {
                minScore = Math.min(minScore, road[2]);
            }
        }
        
        return minScore;
    }
}