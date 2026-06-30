class Solution {
    public int repeatedStringMatch(String a, String b) {
        int n = a.length();
        int m = b.length();
        // Maximum repetitions needed: ceil(m/n) + 2
        int maxRep = (m / n) + 2;
        StringBuilder sb = new StringBuilder();
        for (int rep = 1; rep <= maxRep; rep++) {
            sb.append(a);
            if (sb.length() >= m && sb.indexOf(b) != -1) {
                return rep;
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.repeatedStringMatch("abcd", "cdabcdab")); // 3

        // Example 2
        System.out.println(sol.repeatedStringMatch("a", "aa"));          // 2

        // Additional test
        System.out.println(sol.repeatedStringMatch("abc", "wxyz"));     // -1
    }
}