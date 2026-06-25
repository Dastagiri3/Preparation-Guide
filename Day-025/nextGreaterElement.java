import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Map to store next greater element for each value in nums2
        Map<Integer, Integer> ngeMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        // Traverse nums2 to find next greater for each element
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                ngeMap.put(stack.pop(), num);
            }
            stack.push(num);
        }
        // Remaining elements have no greater element -> they stay -1
        
        // Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = ngeMap.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1: nums1 = [1,3,2,4], nums2 = [1,3,2,4]
        int[] nums1 = {1, 3, 2, 4};
        int[] nums2 = {1, 3, 2, 4};
        System.out.println(Arrays.toString(sol.nextGreaterElement(nums1, nums2))); // [3, 4, 4, -1]

        // Example 2: nums1 = [6,8,0,1,3], nums2 = [6,8,0,1,3]
        int[] nums1_2 = {6, 8, 0, 1, 3};
        int[] nums2_2 = {6, 8, 0, 1, 3};
        System.out.println(Arrays.toString(sol.nextGreaterElement(nums1_2, nums2_2))); // [8, -1, 1, 3, -1]
    }
}