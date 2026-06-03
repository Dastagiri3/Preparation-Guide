import java.util.*;

class Solution {
    public List<List<Integer>> merge(List<List<Integer>> intervals) {
        // Sort intervals based on the start value
        intervals.sort(Comparator.comparingInt(a -> a.get(0)));

        List<List<Integer>> merged = new ArrayList<>();

        for (List<Integer> interval : intervals) {
            // If merged is empty or current interval does not overlap with the last merged
            // interval
            if (merged.isEmpty() || merged.get(merged.size() - 1).get(1) < interval.get(0)) {
                // No overlap: add current interval as a new entry
                merged.add(new ArrayList<>(interval));
            } else {
                // Overlap: merge by updating the end of the last interval
                List<Integer> last = merged.get(merged.size() - 1);
                last.set(1, Math.max(last.get(1), interval.get(1)));
            }
        }
        return merged;
    }
}