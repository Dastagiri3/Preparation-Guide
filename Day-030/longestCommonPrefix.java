class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // If index out of bounds for some string or character mismatch
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }
        return first; // entire first string is common prefix
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println(sol.longestCommonPrefix(strs1)); // "fl"

        // Example 2
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println(sol.longestCommonPrefix(strs2)); // ""

        // Additional test
        String[] strs3 = {"a"};
        System.out.println(sol.longestCommonPrefix(strs3)); // "a"
    }
}