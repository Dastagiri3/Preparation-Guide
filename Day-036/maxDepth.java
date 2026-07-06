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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

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
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [3,9,20,null,null,15,7] -> depth 3
        TreeNode root1 = Solution.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Example 1: " + sol.maxDepth(root1)); // 3

        // Example 2: [1,null,2] -> depth 2
        TreeNode root2 = Solution.buildTree(new Integer[]{1, null, 2});
        System.out.println("Example 2: " + sol.maxDepth(root2)); // 2

        // Edge case: empty tree -> depth 0
        TreeNode root3 = null;
        System.out.println("Empty tree: " + sol.maxDepth(root3)); // 0
    }
}