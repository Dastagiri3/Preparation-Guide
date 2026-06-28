import java.util.*;

class StockSpanner {
    private Stack<int[]> stack; // each entry: [price, span]

    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        // Pop all elements with price <= current price, accumulating their spans
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;
    }

    public static void main(String[] args) {
        StockSpanner sp = new StockSpanner();
        // Test case from example: [100, 80, 60, 70, 60, 75, 85]
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        List<Integer> outputs = new ArrayList<>();
        for (int p : prices) {
            outputs.add(sp.next(p));
        }
        System.out.println(outputs); // [1, 1, 1, 2, 1, 4, 6]
    }
}