/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {

            // Find kth node
            ListNode kth = getKthNode(groupPrev, k);

            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;

            // Reverse group
            ListNode prev = groupNext;
            ListNode current = groupPrev.next;

            while (current != groupNext) {
                ListNode temp = current.next;
                current.next = prev;
                prev = current;
                current = temp;
            }

            // Connect previous part with reversed group
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode current, int k) {

        while (current != null && k > 0) {
            current = current.next;
            k--;
        }

        return current;
    }
}