import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Bucket sort by frequency: index = frequency, value = list of numbers with that frequency
        int n = nums.length;
        List<Integer>[] bucket = new ArrayList[n + 1];
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        // Collect top k frequent elements from highest frequency downward
        int[] result = new int[k];
        int idx = 0;
        for (int i = n; i >= 0 && idx < k; i--) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    result[idx++] = num;
                    if (idx == k) break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println(Arrays.toString(sol.topKFrequent(nums1, k1))); // [1, 2]

        // Example 2
        int[] nums2 = {4, 4, 6, 6, 7};
        int k2 = 2;
        System.out.println(Arrays.toString(sol.topKFrequent(nums2, k2))); // [4, 6]

        // Additional test
        int[] nums3 = {1, 2, 3, 3, 3, 4, 4};
        int k3 = 1;
        System.out.println(Arrays.toString(sol.topKFrequent(nums3, k3))); // [3]
    }
}