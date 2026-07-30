public class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int fullGroups = n / 8;
        int remaining = n % 8;
        int totalPushes = 8 * (fullGroups * (fullGroups + 1)) / 2;
        totalPushes += remaining * (fullGroups + 1);        
        return totalPushes;
    }
}
