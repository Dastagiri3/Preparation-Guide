class Solution {
    public String countAndSay(int n) {
        String current = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;
            for (int j = 1; j < current.length(); j++) {
                if (current.charAt(j) == current.charAt(j - 1)) {
                    count++;
                } else {
                    next.append(count).append(current.charAt(j - 1));
                    count = 1;
                }
            }
            // Append the last group
            next.append(count).append(current.charAt(current.length() - 1));
            current = next.toString();
        }
        return current;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.countAndSay(4)); // "1211"

        // Example 2
        System.out.println(sol.countAndSay(1)); // "1"

        // Additional test
        System.out.println(sol.countAndSay(5)); // "111221"
    }
}