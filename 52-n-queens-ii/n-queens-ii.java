class Solution {
    private int count = 0;

    public int totalNQueens(int n) {
        backtrack(0, 0, 0, 0, n);
        return count;
    }

    private void backtrack(int row, int cols, int diag1, int diag2, int n) {
        if (row == n) {
            count++;
            return;
        }

        // Get all available positions for the current row
        int availablePositions = ((1 << n) - 1) & ~(cols | diag1 | diag2);

        while (availablePositions != 0) {
            // Extract the lowest set bit (rightmost available position)
            int p = availablePositions & -availablePositions;
            
            // Turn off the bit in availablePositions to process the next position
            availablePositions -= p;

            // Recurse to the next row, updating the bitmasks
            backtrack(row + 1, cols | p, (diag1 | p) << 1, (diag2 | p) >> 1, n);
        }
    }
}