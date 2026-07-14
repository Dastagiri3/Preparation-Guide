import java.util.*;

class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        // Balance: maxHeap can have at most one more element than minHeap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        // Example 1
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        System.out.println(mf.findMedian()); // 2.0

        // Example 2
        MedianFinder mf2 = new MedianFinder();
        mf2.addNum(1);
        mf2.addNum(6);
        System.out.println(mf2.findMedian()); // 3.5
        mf2.addNum(3);
        System.out.println(mf2.findMedian()); // 3.0

        // "Now your turn!" example:
        MedianFinder mf3 = new MedianFinder();
        mf3.addNum(1);
        System.out.println(mf3.findMedian()); // 1.0
        mf3.addNum(80);
        mf3.addNum(6);
        System.out.println(mf3.findMedian()); // 6.0
        // Expected output for operations: [null, null, 1.0, null, null, 6.0]
    }
}