import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushLeft(root);
    }

    // Push all left nodes from the given node onto the stack
    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        // If the popped node has a right child, push all left descendants of that child
        if (node.right != null) {
            pushLeft(node.right);
        }
        return node.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

public class BSIIterator {
    // Helper to build a tree from a level‑order array (null for missing nodes)
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null)
            return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode curr = queue.poll();
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                queue.offer(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Build the tree from the example: [7,3,15,null,null,9,20]
        Integer[] arr = { 7, 3, 15, null, null, 9, 20 };
        TreeNode root = buildTree(arr);

        BSTIterator bstIterator = new BSTIterator(root);

        // Simulate the sequence of calls from the example
        List<Object> output = new ArrayList<>();
        output.add(null); // constructor
        output.add(bstIterator.next()); // 3
        output.add(bstIterator.next()); // 7
        output.add(bstIterator.hasNext()); // true
        output.add(bstIterator.next()); // 9
        output.add(bstIterator.hasNext()); // true
        output.add(bstIterator.next()); // 15
        output.add(bstIterator.hasNext()); // true
        output.add(bstIterator.next()); // 20
        output.add(bstIterator.hasNext()); // false

        System.out.println(output);
        // Expected: [null, 3, 7, true, 9, true, 15, true, 20, false]
    }
}