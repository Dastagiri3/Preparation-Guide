class Solution {
    public int[] JobScheduling(int[][] Jobs) {
        int n = Jobs.length;
        Arrays.sort(Jobs, (a, b) -> b[2] - a[2]);
        int maxDeadline = 0;
        for (int[] job : Jobs) {
            maxDeadline = Math.max(maxDeadline, job[1]);
        }
        
        boolean[] slot = new boolean[maxDeadline + 1];
        int count = 0;
        int totalProfit = 0;
        
        for (int[] job : Jobs) {
            int deadline = job[1];
            int profit = job[2];
            
            for (int t = deadline; t > 0; t--) {
                if (!slot[t]) {
                    slot[t] = true;
                    count++;
                    totalProfit += profit;
                    break;
                }
            }
        }
        return new int[]{count, totalProfit};
    }
}