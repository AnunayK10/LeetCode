class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        long currentVal = 1; // Use long to prevent integer overflow during multiplication
        
        for (int i = 0; i <= rowIndex; i++) {
            row.add((int) currentVal);
            // Calculate the next value in the row based on the current value
            currentVal = currentVal * (rowIndex - i) / (i + 1);
        }
        
        return row;
    }
}