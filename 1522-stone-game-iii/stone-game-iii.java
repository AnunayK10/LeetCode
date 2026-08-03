class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int next1 = 0, next2 = 0, next3 = 0;        
        for (int i = n - 1; i >= 0; i--) {
            int currentTake = 0;
            int maxRelativeScore = Integer.MIN_VALUE;
            for (int k = 0; k < 3 && i + k < n; k++) {
                currentTake += stoneValue[i + k];
                int opponentBestFuture = 0;
                if (k == 0) opponentBestFuture = next1;      
                else if (k == 1) opponentBestFuture = next2; 
                else opponentBestFuture = next3;             
                maxRelativeScore = Math.max(maxRelativeScore, currentTake - opponentBestFuture);
            }
            next3 = next2;
            next2 = next1;
            next1 = maxRelativeScore;
        }
        if (next1 > 0) {
            return "Alice";
        } else if (next1 < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
