class Solution {
    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, matchIdx = 0;
        int sLen = s.length(), pLen = p.length();

        while (sIdx < sLen) {
            // 1. Characters match or pattern has '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            } 
            // 2. Pattern has '*', record its index and match 0 characters initially
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                matchIdx = sIdx;
                pIdx++;
            } 
            // 3. Mismatch, but we have a previous '*' to fall back on
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                matchIdx++;
                sIdx = matchIdx;
            } 
            // 4. Mismatch with no '*' to absorb characters
            else {
                return false;
            }
        }

        // Consume any remaining '*' at the end of the pattern
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        return pIdx == pLen;
    }
}