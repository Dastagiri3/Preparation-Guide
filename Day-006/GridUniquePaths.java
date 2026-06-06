class Solution {
    public int uniquePaths(int m, int n) {
        // Use 1D DP array for space optimization
        int[] dp = new int[n];
        // Initialize first row to 1
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        // Compute for subsequent rows
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}