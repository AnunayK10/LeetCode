class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if either rectangle is actually a line (area = 0)
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] || 
            rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }

        // Check if one rectangle is completely outside the other
        boolean isLeft = rec1[2] <= rec2[0];
        boolean isRight = rec1[0] >= rec2[2];
        boolean isBottom = rec1[3] <= rec2[1];
        boolean isTop = rec1[1] >= rec2[3];
        
        // If it's not strictly outside in any direction, they overlap
        return !(isLeft || isRight || isBottom || isTop);
    }
}