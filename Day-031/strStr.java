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
        System.out.println(sol.strStr("sadbutsad", "sad"));

        // Example 2
        System.out.println(sol.strStr("leetcode", "leeto"));

        // Additional test
        System.out.println(sol.strStr("hello", "ll"));
        System.out.println(sol.strStr("aaaaa", "bba"));
        System.out.println(sol.strStr("", ""));
    }
}
