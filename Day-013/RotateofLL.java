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
    public ListNode rotateRight(ListNode head, int k) {
        // Edge cases: empty list or single node
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Step 1: compute length and find the tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // Step 2: reduce k modulo length
        k = k % length;
        if (k == 0) {
            return head;
        }
        
        // Step 3: find the new tail (node at position length - k - 1)
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        
        // Step 4: new head is the next node after newTail
        ListNode newHead = newTail.next;
        
        // Step 5: break the list after newTail
        newTail.next = null;
        
        // Step 6: link the original tail to the original head
        tail.next = head;
        
        return newHead;
    }
}