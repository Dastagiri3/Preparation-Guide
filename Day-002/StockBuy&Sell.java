class Solution {
    public int stockBuySell(int[] arr, int n) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < n; i++) {
            // Update the minimum price seen so far
            if (arr[i] < minPrice) {
                minPrice = arr[i];
            }
            // Calculate profit if selling on current day
            else {
                int profit = arr[i] - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}