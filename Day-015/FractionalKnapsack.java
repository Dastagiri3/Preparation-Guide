class Solution {
    static class Item {
       int value;
       int weight;
       double ratio;
       Item(int v, int w) {
           value = v;
           weight = w;
           ratio = (double) v / w;
        }
    }
    public double fractionalKnapsack(int[] val, int[] wt, long cap) {
        int n = val.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(val[i], wt[i]);
        }

        // Sort items by ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0.0;
        double remainingCapacity = cap; // use double for fractional parts

        for (Item item : items) {
            if (remainingCapacity == 0) break;

            if (item.weight <= remainingCapacity) {
                // Take the whole item
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                // Take a fraction of the item
                totalValue += item.value * (remainingCapacity / item.weight);
                remainingCapacity = 0;
                break;
            }
        }

        return totalValue;
    }
}