import java.util.HashMap;

class Solution {
    public int longestSubarray(int[] nums, int k) {
        HashMap<Long, Integer> prefixSumIndex = new HashMap<>();
        long prefixSum = 0;
        int maxLength = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            // Check if the entire prefix sum equals k
            if (prefixSum == k) {
                maxLength = i + 1;
            }
            
            // Check if there is a prefix sum that when removed gives sum k
            long needed = prefixSum - k;
            if (prefixSumIndex.containsKey(needed)) {
                int length = i - prefixSumIndex.get(needed);
                maxLength = Math.max(maxLength, length);
            }
            
            // Store the first occurrence of each prefix sum
            if (!prefixSumIndex.containsKey(prefixSum)) {
                prefixSumIndex.put(prefixSum, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] nums1 = {10, 5, 2, 7, 1, 9};
        int k1 = 15;
        System.out.println("Input: " + java.util.Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + sol.longestSubarray(nums1, k1)); // 4
        
        // Example 2
        int[] nums2 = {-3, 2, 1};
        int k2 = 6;
        System.out.println("Input: " + java.util.Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + sol.longestSubarray(nums2, k2)); // 0
        
        // Additional test with negative numbers
        int[] nums3 = {1, -1, 5, -2, 3};
        int k3 = 3;
        System.out.println("Input: " + java.util.Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + sol.longestSubarray(nums3, k3)); // 4 (1 + -1 + 5 + -2 = 3? Actually 1-1+5-2=3, length 4)
    }
}