class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    
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
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}