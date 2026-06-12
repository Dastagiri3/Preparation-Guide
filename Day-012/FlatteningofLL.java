class Solution {
    public ListNode flattenLinkedList(ListNode head) {
        if (head == null) return null;
        
        // Min-heap to always extract the smallest node
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        
        // Traverse the main list via 'next' and collect all nodes (including child lists)
        ListNode current = head;
        while (current != null) {
            // Add current main node
            heap.offer(current);
            // Traverse its child list and add all nodes
            ListNode childNode = current.child;
            while (childNode != null) {
                heap.offer(childNode);
                childNode = childNode.child;
            }
            current = current.next;
        }
        
        // Build the flattened sorted list using 'child' pointers
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            tail.child = smallest;
            tail = smallest;
            // Clean up the 'next' pointer to avoid any stray links
            smallest.next = null;
        }
        tail.child = null; // last node's child points to null
        return dummy.child;
    }
}