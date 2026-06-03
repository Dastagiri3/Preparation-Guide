class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // pointer for last valid element in nums1
        int j = n - 1; // pointer for last element in nums2
        int k = m + n - 1; // pointer for last position in nums1

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If any elements remain in nums2, copy them
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
        // (Elements left in nums1 are already in place)
    }
}