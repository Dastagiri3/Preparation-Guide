import java.util.*;

class ArrayQueue {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private static final int CAPACITY = 1000; // enough for up to 100 calls

    public ArrayQueue() {
        arr = new int[CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void push(int x) {
        if (size == CAPACITY) {
            // Queue full – ignore (not expected)
            return;
        }
        arr[rear] = x;
        rear = (rear + 1) % CAPACITY;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            return -1; // or throw exception
        }
        int val = arr[front];
        front = (front + 1) % CAPACITY;
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        // Example 1: push 5, push 10, peek, pop, isEmpty
        ArrayQueue queue = new ArrayQueue();
        List<Object> output1 = new ArrayList<>();
        output1.add(null);          // constructor
        queue.push(5);
        output1.add(null);          // push(5)
        queue.push(10);
        output1.add(null);          // push(10)
        output1.add(queue.peek());  // 5
        output1.add(queue.pop());   // 5
        output1.add(queue.isEmpty());// false
        System.out.println(output1);
        // Expected: [null, null, null, 5, 5, false]

        // Example 2: isEmpty
        ArrayQueue queue2 = new ArrayQueue();
        List<Object> output2 = new ArrayList<>();
        output2.add(null);
        output2.add(queue2.isEmpty()); // true
        System.out.println(output2);
        // Expected: [null, true]

        // Now your turn: push(1), pop, isEmpty
        ArrayQueue queue3 = new ArrayQueue();
        List<Object> output3 = new ArrayList<>();
        output3.add(null);               // constructor
        queue3.push(1);
        output3.add(null);               // push(1)
        output3.add(queue3.pop());       // 1
        output3.add(queue3.isEmpty());   // true
        System.out.println(output3);
        // Expected: [null, null, 1, true]  => second option
    }
}