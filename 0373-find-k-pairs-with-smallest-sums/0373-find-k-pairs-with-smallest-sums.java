import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(
            int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();

        // Min Heap
        // Stores indices: [index in nums1, index in nums2]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) ->
                Integer.compare(
                    nums1[a[0]] + nums2[a[1]],
                    nums1[b[0]] + nums2[b[1]]
                )
        );

        // Add first element of each row
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            pq.add(new int[]{i, 0});
        }

        // Find k smallest pairs
        while (!pq.isEmpty() && result.size() < k) {

            int[] current = pq.poll();

            int i = current[0];
            int j = current[1];

            // Add current pair to result
            result.add(
                Arrays.asList(nums1[i], nums2[j])
            );

            // Move to next element in nums2
            if (j + 1 < nums2.length) {
                pq.add(new int[]{i, j + 1});
            }
        }

        return result;
    }
}