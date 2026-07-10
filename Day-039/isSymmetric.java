import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // Helper: checks if two trees are mirror images of each other
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }
}

public class Main {
    // Helper to build a binary tree from a level-order array (null for missing nodes)
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;
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
        Solution sol = new Solution();

        // Example 1: [1,2,2,3,4,4,3] -> true
        TreeNode root1 = buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        System.out.println("Example 1: " + sol.isSymmetric(root1)); // true

        // Example 2: [1,2,2,null,3,null,3] -> false
        TreeNode root2 = buildTree(new Integer[]{1, 2, 2, null, 3, null, 3});
        System.out.println("Example 2: " + sol.isSymmetric(root2)); // false

        // Edge case: single node -> true
        TreeNode root3 = buildTree(new Integer[]{1});
        System.out.println("Single node: " + sol.isSymmetric(root3)); // true

        // Edge case: empty tree (root null) -> true (though constraints say at least one node)
        System.out.println("Empty tree: " + sol.isSymmetric(null)); // true
    }
}