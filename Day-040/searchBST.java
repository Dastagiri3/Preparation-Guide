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

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == val)
                return curr;
            if (val < curr.val)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return null;
    }
}

public class Main {
    // Helper to print tree in level order (for easy verification)
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        java.util.List<String> result = new java.util.ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add("null");
                continue;
            }
            result.add(String.valueOf(node.val));
            queue.offer(node.left);
            queue.offer(node.right);
        }
        // Remove trailing nulls for cleaner output
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i);
            i--;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: root = [4,2,7,1,3], val = 2 → should return [2,1,3]
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);

        TreeNode result1 = sol.searchBST(root1, 2);
        System.out.print("Example 1 (val=2): ");
        printTree(result1); // Expected: [2, 1, 3]

        // Example 2: root = [4,2,7,1,3], val = 5 → should return []
        TreeNode result2 = sol.searchBST(root1, 5);
        System.out.print("Example 2 (val=5): ");
        printTree(result2); // Expected: []
    }
}