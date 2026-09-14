class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        int[] freq = new int[n + 1];
        int commonCount = 0;
        
        for (int i = 0; i < n; i++) {
            // Process the element from array A
            freq[A[i]]++;
            if (freq[A[i]] == 2) {
                commonCount++;
            }
            
            // Process the element from array B
            freq[B[i]]++;
            if (freq[B[i]] == 2) {
                commonCount++;
            }
            
            // Store the current count of common elements
            C[i] = commonCount;
        }
        
        return C;
    }
}