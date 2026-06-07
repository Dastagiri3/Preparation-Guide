import java.util.*;

class MajorityElements2 {
    public List<Integer> majorityElementTwo(int[] nums) {
        int n = nums.length;
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        
        for (int num : nums) {
            if (count1 == 0 && num != candidate2) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0 && num != candidate1) {
                candidate2 = num;
                count2 = 1;
            } else if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }
        
        List<Integer> result = new ArrayList<>();
        if (count1 > n / 3) result.add(candidate1);
        if (count2 > n / 3) result.add(candidate2);
        
        return result;
    }

    public static void main(String[] args) {
        MajorityElements2 me2 = new MajorityElements2();

        // Example 1
        int[] nums1 = {1, 2, 1, 1, 3, 2};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + me2.majorityElementTwo(nums1));

        // Example 2
        int[] nums2 = {1, 2, 1, 1, 3, 2, 2};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + me2.majorityElementTwo(nums2));
    }
}