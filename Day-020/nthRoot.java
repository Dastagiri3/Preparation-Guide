class Solution {
    public int nthRoot(int N, int M) {
        int low = 1, high = M;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = comparePower(mid, N, M);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    
    // Returns 0 if mid^N == M, -1 if mid^N < M, 1 if mid^N > M
    private int comparePower(int x, int N, int M) {
        long result = 1;
        for (int i = 0; i < N; i++) {
            result *= x;
            if (result > M) return 1; // early overflow
        }
        return Long.compare(result, M);
    }
}