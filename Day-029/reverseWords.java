import java.util.*;

class Solution {
    public String reverseWords(String s) {
        // Trim leading/trailing spaces and split by one or more spaces
        String[] words = s.trim().split("\\s+");
        // Reverse the array of words
        int left = 0, right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }
        // Join words with single space
        return String.join(" ", words);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.reverseWords("the sky is blue")); // "blue is sky the"

        // Example 2
        System.out.println(sol.reverseWords("  hello world  ")); // "world hello"

        // Example 3
        System.out.println(sol.reverseWords("a good   example")); // "example good a"
    }
}