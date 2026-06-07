import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int longestStreak = 0;
        
        for (int num : numSet) {
            // Only start counting if it's the beginning of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Input: " + java.util.Arrays.toString(nums1));
        System.out.println("Output: " + sol.longestConsecutive(nums1)); // 4
        
        // Example 2
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("Input: " + java.util.Arrays.toString(nums2));
        System.out.println("Output: " + sol.longestConsecutive(nums2)); // 9
        
        // Additional test from the prompt
        int[] nums3 = {1, 9, 3, 10, 4, 20, 2};
        System.out.println("Input: " + java.util.Arrays.toString(nums3));
        System.out.println("Output: " + sol.longestConsecutive(nums3)); // 4 (1,2,3,4)
    }
}