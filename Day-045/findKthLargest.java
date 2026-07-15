import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min‑heap to keep the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove the smallest among the k+1
            }
        }
        // The root of the heap is the k‑th largest
        return minHeap.peek();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println(sol.findKthLargest(nums1, k1)); // 5

        // Example 2
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println(sol.findKthLargest(nums2, k2)); // 4
    }
}