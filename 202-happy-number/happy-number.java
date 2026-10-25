class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        
        // Loop until fast reaches 1 (happy) or slow meets fast (cycle detected)
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        
        return fast == 1;
    }
    
    // Helper method to compute the sum of the squares of the digits
    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}