class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // dp[i][j] = minimum insertions to make s[i..j] palindrome
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.minInsertions("zzazz"));

        // Example 2
        System.out.println(sol.minInsertions("mbadm"));

        // Example 3
        System.out.println(sol.minInsertions("leetcode"));

        // Additional test
        System.out.println(sol.minInsertions("a"));
        System.out.println(sol.minInsertions("ab")); 
    }
}
