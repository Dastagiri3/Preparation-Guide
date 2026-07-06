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
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        diameter = Math.max(diameter, leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Helper to build a binary tree from a level-order array (null for missing)
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

        // Example 1: [1,2,3,4,5] -> diameter 3
        TreeNode root1 = Solution.buildTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("Example 1: " + sol.diameterOfBinaryTree(root1)); // 3

        // Example 2: [1,2] -> diameter 1
        TreeNode root2 = Solution.buildTree(new Integer[]{1, 2});
        System.out.println("Example 2: " + sol.diameterOfBinaryTree(root2)); // 1

        // Additional test: single node -> diameter 0
        TreeNode root3 = Solution.buildTree(new Integer[]{1});
        System.out.println("Single node: " + sol.diameterOfBinaryTree(root3)); // 0
    }
}