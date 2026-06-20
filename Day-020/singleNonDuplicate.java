class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // Ensure mid is even so that mid and mid+1 are a pair
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid + 1]) {
                // The single element is on the right side
                left = mid + 2;
            } else {
                // The single element is on the left side (including mid)
                right = mid;
            }
        }
        return nums[left];
    }
}