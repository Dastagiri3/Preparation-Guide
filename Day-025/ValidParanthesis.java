import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Example 1
        System.out.println(sol.isValid("()[]{}")); // true
        // Example 2
        System.out.println(sol.isValid("[(])"));   // false
        // Additional
        System.out.println(sol.isValid("([{}])")); // true
        System.out.println(sol.isValid("(("));     // false
    }
}