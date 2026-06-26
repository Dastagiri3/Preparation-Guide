import java.util.*;

class Solution {
    public int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements that are >= current, as they can't be the next smaller
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            // The top of the stack is the next smaller element
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            // Push current element for future comparisons
            stack.push(arr[i]);
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] arr1 = {4, 8, 5, 2, 25};
        int[] res1 = sol.nextSmallerElement(arr1);
        System.out.println(Arrays.toString(res1)); // [2, 5, 2, -1, -1]
        
        // Example 2
        int[] arr2 = {10, 9, 8, 7};
        int[] res2 = sol.nextSmallerElement(arr2);
        System.out.println(Arrays.toString(res2)); // [9, 8, 7, -1]
        
        // "Now your turn!" – strictly increasing array: no smaller to the right
        int[] arr3 = {1, 2, 3, 4, 5};
        int[] res3 = sol.nextSmallerElement(arr3);
        System.out.println(Arrays.toString(res3)); // [-1, -1, -1, -1, -1]
    }
}