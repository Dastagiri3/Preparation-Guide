class Solution {
    public boolean isSubsetSum(int[] arr, int target) {
       boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : arr) {
            for (int i = target; i >= num; i--) {
                if (dp[i - num]) {
                    dp[i] = true;
                }
            }
        }
        return dp[target];
    }
}
