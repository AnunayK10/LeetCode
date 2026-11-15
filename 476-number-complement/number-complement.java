class Solution {
    public int findComplement(int num) {
        // Create a mask with all 1s for the length of num's binary representation
        int mask = 0;
        int temp = num;
        
        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }
        
        return num ^ mask;
    }
}