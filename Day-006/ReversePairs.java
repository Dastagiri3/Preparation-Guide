import java.util.Arrays;

class Solution {
    public int reversePairs(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }
    
    private int mergeSortAndCount(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int count = mergeSortAndCount(nums, left, mid) 
                  + mergeSortAndCount(nums, mid + 1, right);
        
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if ((long) nums[i] > 2L * (long) nums[j]) {
                count += (mid - i + 1);
                j++;
            } else {
                i++;
            }
        }
        merge(nums, left, mid, right);
        return count;
    }
    
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = (nums[i] <= nums[j]) ? nums[i++] : nums[j++];
        }
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] nums1 = {6, 4, 1, 2, 7};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + sol.reversePairs(nums1));  // 3
        
        // Example 2
        int[] nums2 = {5, 4, 4, 3, 3};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + sol.reversePairs(nums2));  // 0
    }
}