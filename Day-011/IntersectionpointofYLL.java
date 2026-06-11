import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

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

public class IntersectionpointofYLL {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }
}