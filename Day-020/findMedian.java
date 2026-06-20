class Solution {
    public int findMedian(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int total = n * m;
        int target = (total + 1) / 2; // median position (1-indexed)
        int low = 1, high = 1000000000; // constraints: 1 <= matrix[i] <= 1e9
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countLessEqual(matrix, mid);
            if (count >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    private int countLessEqual(int[][] matrix, int x) {
        int count = 0;
        for (int[] row : matrix) {
            // Binary search in each sorted row
            int lo = 0, hi = row.length; // hi exclusive
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (row[mid] <= x) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            count += lo;
        }
        return count;
    }
}