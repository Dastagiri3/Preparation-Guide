import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices
        
        for (int i = 0; i < n; i++) {
            // Remove indices outside the current window
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            // Remove smaller elements from the back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            // The front of deque is the maximum of the current window
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(Arrays.toString(sol.maxSlidingWindow(nums1, k1))); // [3, 3, 5, 5, 6, 7]
        
        // Example 2
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(Arrays.toString(sol.maxSlidingWindow(nums2, k2))); // [1]
    }
}