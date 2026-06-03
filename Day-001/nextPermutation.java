class Solution {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;
        // Step 1: Find the first decreasing element from the right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If the entire array is not decreasing
        if (i >= 0) {
            int j = nums.length - 1;
            // Find the element just larger than nums[i] from the right
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap them
            swap(nums, i, j);
        }

        // Step 3: Reverse the elements after index i to get the next lexicographical
        // order
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Test Case 1 ===");
        int[] nums1 = { 1, 2, 3 };
        System.out.print("Original Array: ");
        printArray(nums1);
        nextPermutation(nums1);
        System.out.print("Next Permutation: ");
        printArray(nums1);
        System.out.println("\n=== Test Case 2 ===");
        int[] nums2 = { 3, 2, 1 };
        System.out.print("Original Array: ");
        printArray(nums2);
        nextPermutation(nums2);
        System.out.print("Next Permutation: ");
        printArray(nums2);
        System.out.println("\n=== Test Case 3 ===");
        int[] nums3 = { 1, 1, 5 };
        System.out.print("Original Array: ");
        printArray(nums3);
        nextPermutation(nums3);
        System.out.print("Next Permutation: ");
        printArray(nums3);
        System.out.println("\n=== Test Case 4 ===");
        int[] nums4 = { 1, 3, 2 };
        System.out.print("Original Array: ");
        printArray(nums4);
        nextPermutation(nums4);
        System.out.print("Next Permutation: ");
        printArray(nums4);
    }

    private static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}