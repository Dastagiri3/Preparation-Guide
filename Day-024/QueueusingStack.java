import java.util.*;

class MyQueue {
    private Stack<Integer> input;  // for push operations
    private Stack<Integer> output; // for pop/peek

    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    // Push element x to the back of the queue.
    public void push(int x) {
        input.push(x);
    }

    // Removes the element from the front of the queue and returns it.
    public int pop() {
        ensureOutput();
        return output.pop();
    }

    // Returns the element at the front of the queue.
    public int peek() {
        ensureOutput();
        return output.peek();
    }

    // Returns true if the queue is empty, false otherwise.
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    // Helper: move all elements from input to output if output is empty.
    private void ensureOutput() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek()); // 1
        System.out.println(queue.pop());  // 1
        System.out.println(queue.empty()); // false
        System.out.println(queue.pop());  // 2
        System.out.println(queue.empty()); // true
    }
}