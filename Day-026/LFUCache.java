import java.util.*;

class LFUCache {
    private final int capacity;
    private int minFreq;
    private final Map<Integer, Integer> keyToVal;
    private final Map<Integer, Integer> keyToFreq;
    private final Map<Integer, LinkedHashSet<Integer>> freqToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToVal = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) return -1;
        // increase frequency
        int freq = keyToFreq.get(key);
        // remove from current freq set
        freqToKeys.get(freq).remove(key);
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            if (minFreq == freq) minFreq++;
        }
        // add to freq+1 set
        int newFreq = freq + 1;
        keyToFreq.put(key, newFreq);
        freqToKeys.computeIfAbsent(newFreq, k -> new LinkedHashSet<>()).add(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (keyToVal.containsKey(key)) {
            // update value and increase frequency
            keyToVal.put(key, value);
            get(key); // reuse get to increment frequency and update sets
            return;
        }
        // if cache full, evict LFU (and LRU within same freq)
        if (keyToVal.size() == capacity) {
            // get the least frequent set
            LinkedHashSet<Integer> keys = freqToKeys.get(minFreq);
            int evictKey = keys.iterator().next(); // LRU within that freq
            keys.remove(evictKey);
            if (keys.isEmpty()) {
                freqToKeys.remove(minFreq);
                // minFreq will be set to 1 after insertion
            }
            keyToVal.remove(evictKey);
            keyToFreq.remove(evictKey);
        }
        // insert new key with freq 1
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        minFreq = 1;
        freqToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
    }
}

public class Main {
    // Process operations: each op is an array where op[0] is operation code:
    // 1 = put, 2 = get
    public static List<Object> process(LFUCache cache, int[][] ops) {
        List<Object> result = new ArrayList<>();
        for (int[] op : ops) {
            if (op[0] == 1) { // put
                cache.put(op[1], op[2]);
                result.add(null);
            } else if (op[0] == 2) { // get
                result.add(cache.get(op[1]));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example 1
        LFUCache cache1 = new LFUCache(2);
        int[][] ops1 = {
            {1, 1, 1},
            {1, 2, 2},
            {2, 1},
            {1, 3, 3},
            {2, 2},
            {2, 3},
            {1, 4, 4},
            {2, 1},
            {2, 3},
            {2, 4}
        };
        System.out.println(process(cache1, ops1));
        // Expected: [null, null, 1, null, -1, 3, null, -1, 3, 4]

        // Example 2
        LFUCache cache2 = new LFUCache(3);
        int[][] ops2 = {
            {1, 5, 7},
            {1, 4, 6},
            {1, 3, 5},
            {1, 2, 4},
            {2, 1},
            {2, 2},
            {2, 3},
            {2, 4},
            {2, 5}
        };
        System.out.println(process(cache2, ops2));
        // Expected: [null, null, null, null, 3, 4, 5, -1, -1]

        // Now your turn (from the screenshot)
        LFUCache cache3 = new LFUCache(2);
        int[][] ops3 = {
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
        System.out.println(process(cache3, ops3));
        // Expected: [null, null, 1, null, -1, null, -1, 3, 4]
    }
}