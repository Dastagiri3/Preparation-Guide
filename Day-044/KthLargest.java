import java.util.PriorityQueue;

class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> minHeap; // stores the k largest elements

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(); // min-heap (default)
        // Add initial numbers
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // If heap size < k, just add the value
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            // If the new value is larger than the smallest among the k largest,
            // replace the smallest with the new value
            minHeap.poll();
            minHeap.offer(val);
        }
        // The root of the min-heap is the k-th largest
        return minHeap.peek();
    }

    public static void main(String[] args) {
        // Example 1
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));  // 4
        System.out.println(kthLargest.add(5));  // 5
        System.out.println(kthLargest.add(10)); // 5
        System.out.println(kthLargest.add(9));  // 8
        System.out.println(kthLargest.add(4));  // 8

        System.out.println(); // separator

        // Example 2
        KthLargest kthLargest2 = new KthLargest(4, new int[]{7, 7, 7, 7, 8, 3});
        System.out.println(kthLargest2.add(2));  // 7
        System.out.println(kthLargest2.add(10)); // 7
        System.out.println(kthLargest2.add(9));  // 7
        System.out.println(kthLargest2.add(4));  // 8
    }
}