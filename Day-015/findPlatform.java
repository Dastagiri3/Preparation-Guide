class Solution {
    public int findPlatform(int[] arrival, int[] departure) {
        int n = arrival.length;
        Arrays.sort(arrival);
        Arrays.sort(departure);
        
        int platforms = 0;
        int maxPlatforms = 0;
        int i = 0, j = 0;
        
        while (i < n && j < n) {
            if (arrival[i] <= departure[j]) {
                platforms++;
                i++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
            } else {
                platforms--;
                j++;
            }
        }
        return maxPlatforms;
    }
}