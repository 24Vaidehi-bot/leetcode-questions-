import java.util.*;

class Solution {
    public int findKthLargest(int[] nums, int k) {

        // Min Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : nums) {

            pq.add(num);

            // Keep only k largest elements
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Smallest among k largest = kth largest
        return pq.peek();
    }
}