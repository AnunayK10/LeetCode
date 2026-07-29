public class Solution {
    public String smallestPalindrome(String s, long k) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int oddCount = 0;
        int midIndex = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midIndex = i;
            }
        }
        if (oddCount > 1) return "";
        int[] halfCount = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
            halfLen += halfCount[i];
        }
        if (getPermutations(halfCount, halfLen, k) < k) {
            return "";
        }
        StringBuilder leftHalf = new StringBuilder();
        int remainingLen = halfLen;
        for (int pos = 0; pos < halfLen; pos++) {
            for (int i = 0; i < 26; i++) {
                if (halfCount[i] > 0) {
                    halfCount[i]--;
                    long options = getPermutations(halfCount, remainingLen - 1, k);           
                    if (k <= options) {
                        leftHalf.append((char) ('a' + i));
                        remainingLen--;
                        break;
                    } else {
                        k -= options;
                        halfCount[i]++;
                    }
                }
            }
        }
        String left = leftHalf.toString();
        String mid = (midIndex == -1) ? "" : String.valueOf((char) ('a' + midIndex));
        String right = new StringBuilder(left).reverse().toString();
        return left + mid + right;
    }
    private long getPermutations(int[] halfCount, int totalItems, long maxLimit) {
        long permutations = 1;
        int currentTotal = 0;

        for (int count : halfCount) {
            if (count == 0) continue;
            for (int j = 1; j <= count; j++) {
                currentTotal++;
                permutations = (permutations * currentTotal) / j;
                if (permutations > maxLimit) {
                    return maxLimit + 1; 
                }
            }
        }
        return permutations;
    }
}
