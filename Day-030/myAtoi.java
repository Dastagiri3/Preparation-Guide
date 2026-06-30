class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        // Step 1: Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i == n) return 0;

        // Step 2: Determine sign
        int sign = 1;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3: Convert digits, stop at non-digit
        long result = 0; // use long to detect overflow
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            // Check for overflow / underflow
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }

        return (int) (sign * result);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.myAtoi("42"));          // 42

        // Example 2
        System.out.println(sol.myAtoi(" -042"));       // -42

        // Example 3
        System.out.println(sol.myAtoi("1337c0d3"));    // 1337

        // Example 4
        System.out.println(sol.myAtoi("0-1"));         // 0

        // Example 5
        System.out.println(sol.myAtoi("words and 987")); // 0

        // Additional edge cases
        System.out.println(sol.myAtoi("-91283472332")); // -2147483648
        System.out.println(sol.myAtoi("2147483648"));   // 2147483647
    }
}