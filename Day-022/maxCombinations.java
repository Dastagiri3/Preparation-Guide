import java.util.*;

class MaxCombinations {
    public List<Integer> maxCombinations(int[] nums1, int[] nums2, int k) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int[] a = nums1.clone();
        int[] b = nums2.clone();
        Arrays.sort(a);
        Arrays.sort(b);
        PriorityQueue<int[]> heap = new PriorityQueue<>((p, q) -> q[0] - p[0]); 
        Set<Long> visited = new HashSet<>();
        int i = n - 1, j = n - 1;
        heap.offer(new int[]{a[i] + b[j], i, j});
        visited.add(((long)i) * n + j);
        
        List<Integer> result = new ArrayList<>();
        while (k-- > 0 && !heap.isEmpty()) {
            int[] cur = heap.poll();
            int sum = cur[0];
            int row = cur[1];
            int col = cur[2];
            result.add(sum);
            // Option 1: move row index up (i-1)
            if (row - 1 >= 0) {
                long key = ((long)(row - 1)) * n + col;
                if (!visited.contains(key)) {
                    visited.add(key);
                    heap.offer(new int[]{a[row - 1] + b[col], row - 1, col});
                }
            }
            // Option 2: move col index down (j-1)
            if (col - 1 >= 0) {
                long key = ((long)row) * n + (col - 1);
                if (!visited.contains(key)) {
                    visited.add(key);
                    heap.offer(new int[]{a[row] + b[col - 1], row, col - 1});
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxCombinations sol = new MaxCombinations();
        
        // Example 1
        int[] nums1 = {7, 3};
        int[] nums2 = {1, 6};
        int k = 2;
        System.out.println(sol.maxCombinations(nums1, nums2, k)); // [13, 9]
        
        // Example 2
        int[] nums1_2 = {3, 4, 5};
        int[] nums2_2 = {2, 6, 3};
        int k2 = 2;
        System.out.println(sol.maxCombinations(nums1_2, nums2_2, k2)); // [11, 10]
    }
}