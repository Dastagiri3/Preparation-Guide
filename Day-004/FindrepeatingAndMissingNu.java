class Solution {
    public int[] findMissingRepeatingNumbers(int[] nums) {
        int n = nums.length;
        
        // Step 1: XOR all array elements and numbers from 1 to n
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        // xor = repeating ^ missing (A ^ B)
        
        // Step 2: Find the rightmost set bit in xor
        int rightmostSetBit = xor & -xor;
        
        // Step 3: Divide numbers into two groups based on the rightmost set bit
        int x = 0, y = 0;
        for (int num : nums) {
            if ((num & rightmostSetBit) != 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & rightmostSetBit) != 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }
        // Now x and y are the two numbers (one repeating, one missing)
        
        // Step 4: Identify which is repeating and which is missing
        for (int num : nums) {
            if (num == x) {
                return new int[]{x, y};  // x is repeating, y is missing
            }
        }
        return new int[]{y, x};  // y is repeating, x is missing
    }
}