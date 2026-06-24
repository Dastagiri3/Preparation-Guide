import java.util.*;

class MyStack {
    private Queue<Integer> q1; // main queue holding stack elements
    private Queue<Integer> q2; // helper queue for reordering

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    // Push element x onto stack.
    public void push(int x) {
        // Always add to q2
        q2.offer(x);
        // Move all elements from q1 to q2 (so q2 has the new element at front)
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        // Swap references: q1 now contains the stack order (top at front)
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Removes the element on top of the stack and returns it.
    public int pop() {
        return q1.poll();
    }

    // Returns the element on top of the stack.
    public int top() {
        return q1.peek();
    }

    // Returns true if the stack is empty, false otherwise.
    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        System.out.println("push(1)");
        stack.push(1);
        System.out.println("push(2)");
        stack.push(2);
        System.out.println("top() = " + stack.top());   // 2
        System.out.println("pop() = " + stack.pop());   // 2
        System.out.println("empty() = " + stack.empty()); // false
    }
}