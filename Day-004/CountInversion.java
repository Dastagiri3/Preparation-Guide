class Solution {
    public long numberOfInversions(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    private long mergeSortAndCount(int[] nums, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            count += mergeSortAndCount(nums, left, mid);
            count += mergeSortAndCount(nums, mid + 1, right);
            count += mergeAndCount(nums, left, mid, right);
        }
        return count;
    }

    private long mergeAndCount(int[] nums, int left, int mid, int right) {
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Copy data to temporary arrays
        for (int i = 0; i < leftArr.length; i++)
            leftArr[i] = nums[left + i];
        for (int j = 0; j < rightArr.length; j++)
            rightArr[j] = nums[mid + 1 + j];

        int i = 0, j = 0, k = left;
        long swaps = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                nums[k++] = leftArr[i++];
            } else {
                nums[k++] = rightArr[j++];
                // All remaining elements in leftArr are greater than rightArr[j]
                swaps += (mid + 1) - (left + i);
            }
        }

        // Copy remaining elements
        while (i < leftArr.length)
            nums[k++] = leftArr[i++];
        while (j < rightArr.length)
            nums[k++] = rightArr[j++];

        return swaps;
    }
}