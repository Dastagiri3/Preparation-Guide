class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
            if (freq[c - 'a'] < 0) return false;
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.isAnagram("anagram", "nagaram")); // true

        // Example 2
        System.out.println(sol.isAnagram("rat", "car")); // false

        // Additional test
        System.out.println(sol.isAnagram("listen", "silent")); // true
    }
}