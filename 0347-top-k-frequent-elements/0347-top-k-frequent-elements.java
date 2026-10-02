import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // Store frequency of each number
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Min Heap
        // Each element is: [number, frequency]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] - b[1]
        );

        // Add each unique number
        for (int num : map.keySet()) {

            pq.add(new int[]{num, map.get(num)});

            // Keep only k most frequent elements
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Store answer
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }

        return result;
    }
}