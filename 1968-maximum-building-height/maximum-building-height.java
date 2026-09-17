class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        // Create a new array to include the implicit restrictions at the start and end
        int[][] r = new int[m + 2][2];
        
        for (int i = 0; i < m; i++) {
            r[i] = restrictions[i];
        }
        r[m] = new int[]{1, 0};
        r[m + 1] = new int[]{n, n - 1}; 
        
        // Sort restrictions by building ID
        Arrays.sort(r, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Pass 1: Left to right (limit height based on the left neighbor)
        for (int i = 1; i < r.length; i++) {
            r[i][1] = Math.min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0]);
        }
        
        // Pass 2: Right to left (limit height based on the right neighbor)
        for (int i = r.length - 2; i >= 0; i--) {
            r[i][1] = Math.min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0]);
        }
        
        // Find the maximum peak height between any two adjacent restricted buildings
        int maxHeight = 0;
        for (int i = 1; i < r.length; i++) {
            int id1 = r[i - 1][0], h1 = r[i - 1][1];
            int id2 = r[i][0], h2 = r[i][1];
            
            // Peak formula: (distance between buildings + height1 + height2) / 2
            int peak = (id2 - id1 + h1 + h2) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }
        
        return maxHeight;
    }
}