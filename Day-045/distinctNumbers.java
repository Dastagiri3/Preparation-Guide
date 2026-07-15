import java.util.*;

// Solution class containing the logic (non‑public)
class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Map<Integer, Integer> freq = new HashMap<>();
        int distinct = 0;

        // First window
        for (int i = 0; i < k; i++) {
            int val = nums[i];
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            if (freq.get(val) == 1) distinct++;
        }
        ans[0] = distinct;

        // Slide the window
        for (int i = k; i < n; i++) {
            // Remove leftmost
            int leftVal = nums[i - k];
            int count = freq.get(leftVal);
            if (count == 1) {
                freq.remove(leftVal);
                distinct--;
            } else {
                freq.put(leftVal, count - 1);
            }

            // Add rightmost
            int rightVal = nums[i];
            freq.put(rightVal, freq.getOrDefault(rightVal, 0) + 1);
            if (freq.get(rightVal) == 1) distinct++;

            ans[i - k + 1] = distinct;
        }
        return ans;
    }
}

// Public Main class containing the entry point
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] nums1 = {1, 2, 3, 2, 2, 1, 3};
        int k1 = 3;
        System.out.println(Arrays.toString(sol.distinctNumbers(nums1, k1))); // [3, 2, 2, 2, 3]

        // Example 2
        int[] nums2 = {1, 1, 1, 1, 2, 3, 4};
        int k2 = 4;
        System.out.println(Arrays.toString(sol.distinctNumbers(nums2, k2))); // [1, 2, 3, 4]

        // Edge case: k = 1
        int[] nums3 = {5, 5, 6, 7};
        int k3 = 1;
        System.out.println(Arrays.toString(sol.distinctNumbers(nums3, k3))); // [1, 1, 1, 1]
    }
}