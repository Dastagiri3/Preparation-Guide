import java.util.*;

class Solution {
    // Recursively sorts the stack in descending order (largest at top)
    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        sortStack(stack);          // sort the remaining stack
        insertSorted(stack, top);  // insert the popped element in correct position
    }

    // Helper: inserts an element into a sorted stack (descending)
    private static void insertSorted(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || element >= stack.peek()) {
            stack.push(element);
            return;
        }
        int top = stack.pop();
        insertSorted(stack, element);
        stack.push(top);
    }

    public static void main(String[] args) {
        // Example 1: [4,1,3,2] -> [4,3,2,1]
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(4);
        stack1.push(1);
        stack1.push(3);
        stack1.push(2);
        System.out.println("Before sorting: " + stack1);
        sortStack(stack1);
        System.out.println("After sorting (descending): " + stack1);

        // Example 2: [1] -> [1]
        Stack<Integer> stack2 = new Stack<>();
        stack2.push(1);
        System.out.println("Before sorting: " + stack2);
        sortStack(stack2);
        System.out.println("After sorting: " + stack2);
    }
}