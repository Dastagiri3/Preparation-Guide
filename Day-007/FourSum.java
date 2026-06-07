import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return result;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate for first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate for second element
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                int left = j + 1;
                int right = n - 1;
                
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        // Skip duplicates for third and fourth elements
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FourSum sol = new FourSum();

        // Example 1
        int[] nums1 = {1, -2, 3, 5, 7, 9};
        int target1 = 7;
        System.out.println("Input: " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + sol.fourSum(nums1, target1)); // [[-2, 1, 3, 5]]
        
        // Example 2
        int[] nums2 = {7, -7, 1, 2, 14, 3};
        int target2 = 9;
        System.out.println("Input: " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + sol.fourSum(nums2, target2)); // []
        
        // Additional test
        int[] nums3 = {0, 0, 0, 0};
        int target3 = 0;
        System.out.println("Input: " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + sol.fourSum(nums3, target3)); // [[0,0,0,0]]
    }
}