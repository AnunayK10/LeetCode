class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int childPtr = 0;
        int cookiePtr = 0;
        
        while (childPtr < g.length && cookiePtr < s.length) {
            // If the cookie satisfies the child's greed factor
            if (s[cookiePtr] >= g[childPtr]) {
                childPtr++; // Move to the next child
            }
            cookiePtr++; // Always move to the next cookie
        }
        
        return childPtr;
    }
}