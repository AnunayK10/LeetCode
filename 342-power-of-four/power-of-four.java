class Solution {
    public boolean isPowerOfFour(int n) {
        // n must be > 0, a power of two, and have its set bit at an even position
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
}