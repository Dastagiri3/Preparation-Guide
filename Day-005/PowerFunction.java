class Solution {
    public double myPow(double x, int n) {
        long exponent = n;  // Use long to handle Integer.MIN_VALUE
        if (exponent < 0) {
            x = 1 / x;
            exponent = -exponent;
        }
        
        double result = 1.0;
        double currentProduct = x;
        
        while (exponent > 0) {
            if ((exponent & 1) == 1) {  // If current bit is 1
                result *= currentProduct;
            }
            currentProduct *= currentProduct;  // Square for next bit
            exponent >>= 1;  // Shift right
        }
        return result;
    }
}