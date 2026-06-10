class ListNode {
    int val;                 // Changed from 'data' to 'val' as expected by driver
    ListNode next;
    ListNode() {}
    ListNode(int x) { val = x; }
    ListNode(int x, ListNode next) { val = x; this.next = next; }
    
    // Deserialize method required by the driver
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) { // Fixed parameter names
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;     // Use 'val' instead of 'data'
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        return dummy.next;
    }
}