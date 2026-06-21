import java.util.Arrays;

public class agressiveCows {
    public int aggressiveCows(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        
        int low = 0;
        int high = nums[n - 1] - nums[0];
        int answer = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlaceCows(nums, k, mid)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }
    
    private boolean canPlaceCows(int[] nums, int k, int distance) {
        int count = 1; // place first cow at first position
        int lastPosition = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - lastPosition >= distance) {
                count++;
                lastPosition = nums[i];
                if (count >= k) return true;
            }
        }
        return false;
    }
}
