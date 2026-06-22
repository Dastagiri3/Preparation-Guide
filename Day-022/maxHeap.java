import java.util.*;

class Solution {
    private ArrayList<Integer> heap;

    // Initialize an empty heap
    public void initializeHeap() {
        heap = new ArrayList<>();
    }

    // Insert a value into the heap
    public void insert(int x) {
        heap.add(x);
        bubbleUp(heap.size() - 1);
    }

    // Get the maximum element (root)
    public int getMax() {
        if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap.get(0);
    }

    // Remove and return the maximum element
    public int extractMax() {
        if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");
        int max = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            bubbleDown(0);
        }
        return max;
    }

    // Return the current size of the heap
    public int heapSize() {
        return heap.size();
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Update the value at the given index to val and fix the heap
    public void changeKey(int ind, int val) {
        if (ind < 0 || ind >= heap.size()) throw new IllegalArgumentException("Index out of bounds");
        heap.set(ind, val);
        // If new value is greater than parent, bubble up; else bubble down
        if (ind > 0 && heap.get(ind) > heap.get((ind - 1) / 2)) {
            bubbleUp(ind);
        } else {
            bubbleDown(ind);
        }
    }

    // Helper: bubble up to maintain heap property
    private void bubbleUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) <= heap.get(parent)) break;
            Collections.swap(heap, i, parent);
            i = parent;
        }
    }

    // Helper: bubble down to maintain heap property
    private void bubbleDown(int i) {
        int n = heap.size();
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;
            if (left < n && heap.get(left) > heap.get(largest)) {
                largest = left;
            }
            if (right < n && heap.get(right) > heap.get(largest)) {
                largest = right;
            }
            // If both children exist and are equal, choose the left child
            if (left < n && right < n && heap.get(left) == heap.get(right)) {
                // left is already chosen if it's greater, but if both equal to parent?
                // We need to ensure left is chosen when left == right.
                // The above code already prefers left because it checks left first.
                // If left == right and both > parent, left will be set as largest.
                // But if parent equals children, no swap needed.
            }
            if (largest == i) break;
            Collections.swap(heap, i, largest);
            i = largest;
        }
    }

    // Main method to demonstrate the heap operations (Example 1)
    public static void main(String[] args) {
        Solution h = new Solution();
        h.initializeHeap();

        // Operation sequence: initializeHeap, insert(4), insert(1), insert(10),
        // getMax, heapSize, isEmpty, extractMax, changeKey(0,16), getMax
        h.insert(4);
        h.insert(1);
        h.insert(10);

        System.out.println(h.getMax());        // 10
        System.out.println(h.heapSize());      // 3
        System.out.println(h.isEmpty() ? 1 : 0); // 0 (false)
        h.extractMax();                        // removes 10
        h.changeKey(0, 16);                   // changes root to 16
        System.out.println(h.getMax());        // 16
    }
}