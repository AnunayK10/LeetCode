class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lengths = new long[n];
        long currentLen = 0;
        
        // Step 1: Forward pass to record the string length after every operation
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                currentLen = Math.max(0, currentLen - 1);
            } else if (c == '#') {
                currentLen *= 2;
            } else if (c == '%') {
                // Reversing doesn't change the length
            } else {
                currentLen++;
            }
            lengths[i] = currentLen;
        }
        
        // If k is out of bounds for the final string length
        if (k >= currentLen) {
            return '.';
        }
        
        // Step 2: Backward pass to trace the origin of the k-th character
        long currK = k;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long prevL = (i > 0) ? lengths[i - 1] : 0;
            
            if (c == '*') {
                // Truncation removes the last character, but doesn't shift indices 
                // for the surviving elements. No update to currK needed.
            } else if (c == '#') {
                // Map currK back to the original half if it falls in the duplicated half
                if (prevL > 0) {
                    currK %= prevL;
                }
            } else if (c == '%') {
                // Map currK to its pre-reversal index
                long L = lengths[i];
                currK = L - 1 - currK;
            } else {
                // c is a lowercase English letter
                if (currK == prevL) {
                    return c;
                }
                // If it isn't this exact appended character, currK < prevL, 
                // so we just continue tracing backward naturally.
            }
        }
        
        return '.';
    }
}