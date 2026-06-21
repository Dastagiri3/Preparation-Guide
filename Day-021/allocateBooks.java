class Solution {
    public int allocateBooks(int[] nums, int m) {
        int n = nums.length;
        // Not enough books for each student
        if (m > n) return -1;

        int low = 0, high = 0;
        for (int pages : nums) {
            low = Math.max(low, pages);  // at least one book per student
            high += pages;               // all books to one student
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canAllocate(nums, m, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canAllocate(int[] nums, int m, int limit) {
        int students = 1;
        int currentSum = 0;
        for (int pages : nums) {
            if (currentSum + pages > limit) {
                // Need a new student for this book
                students++;
                currentSum = pages;
                if (students > m) return false;
            } else {
                currentSum += pages;
            }
        }
        return true;
    }
}