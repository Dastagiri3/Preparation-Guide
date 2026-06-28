import java.util.*;

class Solution {
    public int[] maxOfMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];   // previous smaller (strictly less)
        int[] right = new int[n];  // next smaller or equal (<=)
        Stack<Integer> stack = new Stack<>();

        // Compute previous smaller (strictly less)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();

        // Compute next smaller or equal (<=)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // ans[i] will store the answer for window size (i+1)
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;   // maximum window size where arr[i] is the minimum
            ans[width - 1] = Math.max(ans[width - 1], arr[i]);
        }

        // Propagate maximums downwards: a value that works for a larger window also works for smaller windows
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] arr1 = {10, 20, 30, 50, 10, 70, 30};
        System.out.println(Arrays.toString(sol.maxOfMins(arr1))); // [70, 30, 20, 10, 10, 10, 10]

        // Example 2
        int[] arr2 = {6, 3, 5, 1, 12};
        System.out.println(Arrays.toString(sol.maxOfMins(arr2))); // [12, 3, 3, 1, 1]

        // Additional test
        int[] arr3 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(sol.maxOfMins(arr3))); // [4, 3, 2, 1]
    }
}