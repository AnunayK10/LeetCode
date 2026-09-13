class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // Extract coordinates of all 1s in both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) list1.add(new int[]{i, j});
                if (img2[i][j] == 1) list2.add(new int[]{i, j});
            }
        }

        // 2D array to count frequencies of each translation vector
        // Offset by 'n' to handle negative indices (since max shift is n-1)
        int[][] count = new int[2 * n][2 * n];
        int maxOverlap = 0;

        // Compare every 1 in img1 to every 1 in img2
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int dx = p1[0] - p2[0] + n; 
                int dy = p1[1] - p2[1] + n;
                
                count[dx][dy]++;
                maxOverlap = Math.max(maxOverlap, count[dx][dy]);
            }
        }

        return maxOverlap;
    }
}