class Solution {
    public void sortZeroOneTwo(int[] nums) {
        int low = 0; // boundary for 0s
        int mid = 0; // current element
        int high = nums.length - 1; // boundary for 2s

        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    // swap nums[low] and nums[mid]
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    // swap nums[mid] and nums[high]
                    swap(nums, mid, high);
                    high--;
                    break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}