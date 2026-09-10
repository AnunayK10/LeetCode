class Solution {
    public boolean canReach(int[] arr, int start) {
        // Base case: if out of bounds or already visited (marked negative), return false
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        
        // If we found the target, return true
        if (arr[start] == 0) {
            return true;
        }
        
        // Store the jump distance
        int jump = arr[start];
        
        // Mark the current index as visited by making it negative
        arr[start] = -1;
        
        // Recursively check both possible jumps: right (start + jump) and left (start - jump)
        return canReach(arr, start + jump) || canReach(arr, start - jump);
    }
}