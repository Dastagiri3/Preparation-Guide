import java.util.*;

class LRUCache {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // accessOrder = true → order is based on access (get/put)
        this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;  // evict when capacity exceeded
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}

public class Main {
    // Process the operations and return the result list (null for put, int for get)
    public static List<Object> processOperations(LRUCache cache, int[][] ops) {
        List<Object> result = new ArrayList<>();
        for (int[] op : ops) {
            if (op[0] == 1) { // put
                cache.put(op[1], op[2]);
                result.add(null);
            } else if (op[0] == 2) { // get
                int value = cache.get(op[1]);
                result.add(value);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example 1
        LRUCache cache1 = new LRUCache(2);
        int[][] ops1 = {
            {1, 1, 1},
            {1, 2, 2},
            {2, 1},
            {1, 3, 3},
            {2, 2},
            {1, 4, 4},
            {2, 1},
            {2, 3},
            {2, 4}
        };
        List<Object> out1 = processOperations(cache1, ops1);
        System.out.println(out1);
        // Expected: [null, null, 1, null, -1, null, -1, 3, 4]

        // Example 2
        LRUCache cache2 = new LRUCache(1);
        int[][] ops2 = {
            {1, 1, 1},
            {1, 2, 2},
            {2, 1},
            {1, 3, 3},
            {2, 2},
            {1, 4, 4},
            {2, 3}
        };
        List<Object> out2 = processOperations(cache2, ops2);
        System.out.println(out2);
        // Expected: [null, null, -1, null, -1, null, -1]

        // Now your turn!
        LRUCache cache3 = new LRUCache(2);
        int[][] ops3 = {
            {1, 1, 1},
            {1, 2, 2},
            {2, 1},
            {1, 3, 3},
            {1, 4, 4},
            {2, 2},
            {2, 4}
        };
        List<Object> out3 = processOperations(cache3, ops3);
        System.out.println(out3);
        // Expected: [null, null, 1, null, null, -1, 4]
    }
}