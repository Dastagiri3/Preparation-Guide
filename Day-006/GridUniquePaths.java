class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        System.out.println("m=3, n=2 → " + sol.uniquePaths(3, 2));  // Output: 3
        // Example 2
        System.out.println("m=2, n=4 → " + sol.uniquePaths(2, 4));  // Output: 4
        // Additional test
        System.out.println("m=3, n=3 → " + sol.uniquePaths(3, 3));  // Output: 6
    }
}