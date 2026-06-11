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
class ReverseLLinGroupsize {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        
        // Count total nodes
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        
        curr = head;
        while (count >= k) {
            // Reverse k nodes
            ListNode nextStart = curr;
            ListNode prevGroup = null;
            for (int i = 0; i < k; i++) {
                ListNode nextNode = curr.next;
                curr.next = prevGroup;
                prevGroup = curr;
                curr = nextNode;
            }
            // Connect the reversed group with the rest
            prev.next = prevGroup;
            nextStart.next = curr;
            prev = nextStart;
            count -= k;
        }
        return dummy.next;
    }
}