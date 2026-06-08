import java.util.HashMap;

class Solution {
    public int longestNonRepeatingSubstring(String s) {
        HashMap<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // If character already in window, move left pointer
            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }
            // Update the last occurrence of the character
            lastIndex.put(c, right);
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        String s1 = "abcdabac";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: " + sol.longestNonRepeatingSubstring(s1)); // 4
        
        // Example 2
        String s2 = "aaabbbccc";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + sol.longestNonRepeatingSubstring(s2)); // 2
        
        // Additional test
        String s3 = "abcabcbb";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + sol.longestNonRepeatingSubstring(s3)); // 3 ("abc")
    }
}