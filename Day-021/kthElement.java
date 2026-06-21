class Solution {
    public int kthElement(int[] a, int[] b, int k) {
        int m = a.length, n = b.length;
        // Ensure we binary search on the smaller array for efficiency
        if (m > n) {
            return kthElement(b, a, k);
        }
        
        // We need to pick at least max(0, k-n) elements from a
        int low = Math.max(0, k - n);
        int high = Math.min(k, m);
        
        while (low <= high) {
            int i = low + (high - low) / 2; // number of elements taken from a
            int j = k - i;                  // number of elements taken from b
            
            int maxLeftA  = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int minRightA = (i == m) ? Integer.MAX_VALUE : a[i];
            int maxLeftB  = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int minRightB = (j == n) ? Integer.MAX_VALUE : b[j];
            
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                return Math.max(maxLeftA, maxLeftB);
            } else if (maxLeftA > minRightB) {
                high = i - 1; // too many from a, move left
            } else {
                low = i + 1;  // too few from a, move right
            }
        }
        return -1; // unreachable
    }
}

class KthElementTest {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = {2, 4, 6, 8};
        int[] b = {1, 3, 5, 7, 9, 10};
        int k = 5;
        System.out.println(s.kthElement(a, b, k));
    }
}
