import java.util.*;

class Solution {
    public int[] mergeKSortedArrays(int[][] arr) {
        int k = arr.length;
        if (k == 0) return new int[0];
        int total = k * k; // because each row has size k, but maybe we can use arr[0].length
        // Actually each row is of size k, but we can compute total
        int rows = arr.length;
        int cols = arr[0].length;
        int[] result = new int[rows * cols];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // initialize with first element of each row
        for (int i = 0; i < rows; i++) {
            if (arr[i].length > 0) {
                heap.offer(new int[]{arr[i][0], i, 0});
            }
        }
        int idx = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int val = cur[0], row = cur[1], col = cur[2];
            result[idx++] = val;
            if (col + 1 < arr[row].length) {
                heap.offer(new int[]{arr[row][col + 1], row, col + 1});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Example 1
        int[][] arr1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Arrays.toString(sol.mergeKSortedArrays(arr1))); // [1,2,3,4,5,6,7,8,9]
        // Example 2
        int[][] arr2 = {{1,2,3,4},{2,2,3,4},{5,5,6,6},{7,8,9,9}};
        System.out.println(Arrays.toString(sol.mergeKSortedArrays(arr2))); // [1,2,2,2,3,3,4,4,5,5,6,6,7,8,9,9]
        // k=1
        int[][] arr3 = {{10}};
        System.out.println(Arrays.toString(sol.mergeKSortedArrays(arr3))); // [10]
    }
}