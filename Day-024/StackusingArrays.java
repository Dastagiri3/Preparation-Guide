import java.util.*;

class ArrayStack {
    private int[] arr;
    private int top;
    private static final int MAX_SIZE = 1000; // enough for constraints

    // Constructor
    public ArrayStack() {
        arr = new int[MAX_SIZE];
        top = -1;
    }

    // Push element onto stack
    public void push(int x) {
        if (top < MAX_SIZE - 1) {
            arr[++top] = x;
        }
        // If stack is full, we ignore (not expected per constraints)
    }

    // Pop top element and return it
    public int pop() {
        if (top == -1) return -1; // or throw exception
        return arr[top--];
    }

    // Return top element without removing
    public int top() {
        if (top == -1) return -1;
        return arr[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        // Example 1: operations: ArrayStack, push(5), push(10), top, pop, isEmpty
        ArrayStack stack = new ArrayStack();
        List<Object> result1 = new ArrayList<>();
        result1.add(null);                 // ArrayStack constructor → null
        stack.push(5);
        result1.add(null);                 // push(5) → null
        stack.push(10);
        result1.add(null);                 // push(10) → null
        result1.add(stack.top());          // top() → 10
        result1.add(stack.pop());          // pop() → 10
        result1.add(stack.isEmpty());      // isEmpty() → false
        System.out.println(result1);
        // Expected: [null, null, null, 10, 10, false]

        // Example 2: operations: ArrayStack, isEmpty, push(1), pop, isEmpty
        ArrayStack stack2 = new ArrayStack();
        List<Object> result2 = new ArrayList<>();
        result2.add(null);                 // constructor
        result2.add(stack2.isEmpty());     // isEmpty() → true
        stack2.push(1);
        result2.add(null);                 // push(1) → null
        result2.add(stack2.pop());         // pop() → 1
        result2.add(stack2.isEmpty());     // isEmpty() → true
        System.out.println(result2);
        // Expected: [null, true, null, 1, true]
    }
}