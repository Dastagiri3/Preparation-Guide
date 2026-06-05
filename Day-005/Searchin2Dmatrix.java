class Solution {
    public boolean searchMatrix(int[][] mat, int target) {
        int n = mat.length;        // number of rows
        int m = mat[0].length;     // number of columns
        
        int left = 0;
        int right = n * m - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = mat[mid / m][mid % m];  // map 1D index to 2D coordinates
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}