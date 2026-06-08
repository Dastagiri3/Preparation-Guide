import java.util.HashMap;

class Solution {
    public int subarraysWithXorK(int[] nums, int k) {
        HashMap<Integer, Integer> xorCount = new HashMap<>();
        int prefixXor = 0;
        int count = 0;
        
        xorCount.put(0, 1);
        
        for (int num : nums) {
            prefixXor ^= num;
            
            int target = prefixXor ^ k;
            count += xorCount.getOrDefault(target, 0);
            
            xorCount.put(prefixXor, xorCount.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] nums1 = {4, 2, 2, 6, 4};
        int k1 = 6;
        System.out.println("Input: " + java.util.Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + sol.subarraysWithXorK(nums1, k1)); // 4
        
        // Example 2
        int[] nums2 = {5, 6, 7, 8, 9};
        int k2 = 5;
        System.out.println("Input: " + java.util.Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + sol.subarraysWithXorK(nums2, k2)); // 2
    }
}