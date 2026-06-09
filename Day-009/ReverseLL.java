import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
    // Helper to deserialize a string like "[1,2,3,4,5]" into a linked list
    public static ListNode deserialize(String data) {
        if (data == null || data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (String v : vals) {
            curr.next = new ListNode(Integer.parseInt(v.trim()));
            curr = curr.next;
        }
        return dummy.next;
    }
}

class Solution {
    // Iterative solution (O(n) time, O(1) space)
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    
    // Recursive solution (O(n) time, O(n) recursion stack space)
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    
    // Default method expected by the problem
    public ListNode reverseList(ListNode head) {
        return reverseListIterative(head); // or reverseListRecursive(head)
    }
    
    // Optional main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1: [1,2,3,4,5] -> [5,4,3,2,1]
        ListNode head1 = ListNode.deserialize("[1,2,3,4,5]");
        ListNode reversed1 = sol.reverseList(head1);
        // Print reversed list
        ListNode curr = reversed1;
        System.out.print("Output: [");
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(",");
            curr = curr.next;
        }
        System.out.println("]"); // [5,4,3,2,1]
        
        // Example 2: [1,2] -> [2,1]
        ListNode head2 = ListNode.deserialize("[1,2]");
        ListNode reversed2 = sol.reverseList(head2);
        curr = reversed2;
        System.out.print("Output: [");
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(",");
            curr = curr.next;
        }
        System.out.println("]"); // [2,1]
        
        // Example 3: [] -> []
        ListNode head3 = ListNode.deserialize("[]");
        ListNode reversed3 = sol.reverseList(head3);
        System.out.println(reversed3 == null ? "[]" : "not null");
    }
}