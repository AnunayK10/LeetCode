class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate the angle of the minute hand from 12 o'clock
        double minuteAngle = minutes * 6.0;
        
        // Calculate the angle of the hour hand from 12 o'clock
        // Use (hour % 12) to reset 12 back to 0 degrees.
        double hourAngle = (hour % 12) * 30.0 + (minutes * 0.5);
        
        // Find the absolute difference between the two angles
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // Return the smaller angle (inner angle vs outer angle)
        return Math.min(diff, 360.0 - diff);
    }
}