import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // stores indices
        
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println(sol.largestRectangleArea(heights1)); // 10
        
        // Example 2
        int[] heights2 = {2, 4};
        System.out.println(sol.largestRectangleArea(heights2)); // 4
    }
}