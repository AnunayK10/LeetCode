class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0; // Pointer for nums1
        int j = 0; // Pointer for nums2
        
        // Traverse both arrays
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                // Found the minimum common element
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                // nums1's element is smaller, move pointer i forward
                i++;
            } else {
                // nums2's element is smaller, move pointer j forward
                j++;
            }
        }
        
        // No common element found
        return -1;
    }
}