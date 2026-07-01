class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        // Edge case: needle longer than haystack
        if (m > n) return -1;
        // Sliding window: check each position
        for (int i = 0; i <= n - m; i++) {
            // Efficient substring comparison
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.strStr("sadbutsad", "sad")); // 0

        // Example 2
        System.out.println(sol.strStr("leetcode", "leeto")); // -1

        // Additional test
        System.out.println(sol.strStr("hello", "ll")); // 2
        System.out.println(sol.strStr("aaaaa", "bba")); // -1
        System.out.println(sol.strStr("", "")); // 0 (though constraints say length >=1)
    }
}