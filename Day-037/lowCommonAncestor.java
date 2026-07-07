import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }
}

public class Main {
    // Helper to build a binary tree from a level-order array (null for missing
    // nodes)
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

    // Helper to find a node with a given value (values are unique)
    public static TreeNode findNode(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        TreeNode found = findNode(root.left, val);
        if (found != null)
            return found;
        return findNode(root.right, val);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [3,5,1,6,2,0,8,null,null,7,4], p=5, q=1 -> LCA=3
        Integer[] arr1 = { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
        TreeNode root1 = buildTree(arr1);
        TreeNode p1 = findNode(root1, 5);
        TreeNode q1 = findNode(root1, 1);
        TreeNode lca1 = sol.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Example 1 LCA: " + (lca1 != null ? lca1.val : "null")); // 3

        // Example 2: same tree, p=5, q=4 -> LCA=5
        TreeNode p2 = findNode(root1, 5);
        TreeNode q2 = findNode(root1, 4);
        TreeNode lca2 = sol.lowestCommonAncestor(root1, p2, q2);
        System.out.println("Example 2 LCA: " + (lca2 != null ? lca2.val : "null")); // 5

        // Example 3: [1,2], p=1, q=2 -> LCA=1
        TreeNode root3 = buildTree(new Integer[] { 1, 2 });
        TreeNode p3 = findNode(root3, 1);
        TreeNode q3 = findNode(root3, 2);
        TreeNode lca3 = sol.lowestCommonAncestor(root3, p3, q3);
        System.out.println("Example 3 LCA: " + (lca3 != null ? lca3.val : "null")); // 1
    }
}