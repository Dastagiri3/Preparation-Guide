class Solution {
    public int reversePairs(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }
    
    private int mergeSortAndCount(int[] nums, int left, int right) {
        if (left >= right) return 0;
        
        int mid = left + (right - left) / 2;
        int count = mergeSortAndCount(nums, left, mid) 
                  + mergeSortAndCount(nums, mid + 1, right);
        
        // Count reverse pairs (i in left, j in right) where nums[i] > 2 * nums[j]
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            // Avoid overflow by using long for multiplication
            if ((long) nums[i] > 2L * (long) nums[j]) {
                count += (mid - i + 1);
                j++;
            } else {
                i++;
            }
        }
        
        // Merge the two sorted halves
        merge(nums, left, mid, right);
        return count;
    }
    
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];
        
        System.arraycopy(temp, 0, nums, left, temp.length);
    }
}