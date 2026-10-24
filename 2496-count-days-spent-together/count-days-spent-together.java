class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // Convert all dates to day-of-the-year integers
        int arriveAliceDay = calculateDayOfYear(arriveAlice, daysInMonths);
        int leaveAliceDay = calculateDayOfYear(leaveAlice, daysInMonths);
        int arriveBobDay = calculateDayOfYear(arriveBob, daysInMonths);
        int leaveBobDay = calculateDayOfYear(leaveBob, daysInMonths);
        
        // Find the overlap between the two date ranges
        int startOverlap = Math.max(arriveAliceDay, arriveBobDay);
        int endOverlap = Math.min(leaveAliceDay, leaveBobDay);
        
        // If startOverlap <= endOverlap, they overlap; otherwise, 0 days together
        return Math.max(0, endOverlap - startOverlap + 1);
    }
    
    private int calculateDayOfYear(String date, int[] daysInMonths) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        return totalDays;
    }
}