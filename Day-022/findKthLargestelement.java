import java.util.PriorityQueue;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 2, 3, 4, 5};
        int k1 = 2;
        System.out.println(sol.findKthLargest(nums1, k1)); // 4

        int[] nums2 = {-5, 4, 1, 2, -3};
        int k2 = 5;
        System.out.println(sol.findKthLargest(nums2, k2)); // -5

        int[] nums3 = {11, 9, 8, 7, 3, 1};
        int k3 = 4;
        System.out.println(sol.findKthLargest(nums3, k3)); // 7
    }
}