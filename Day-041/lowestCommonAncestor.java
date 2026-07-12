import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }
}

public class Main {
    // Helper: build a tree from level-order array (null for missing)
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode curr = q.poll();
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                q.offer(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    // Helper: find a node with given value (unique values)
    public static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: root = [6,2,8,0,4,7,9,null,null,3,5], p=2, q=8 → LCA=6
        Integer[] arr1 = {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
        TreeNode root1 = buildTree(arr1);
        TreeNode p1 = findNode(root1, 2);
        TreeNode q1 = findNode(root1, 8);
        TreeNode lca1 = sol.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Example 1 LCA: " + (lca1 != null ? lca1.val : "null")); // 6

        // Example 2: same tree, p=2, q=4 → LCA=2
        TreeNode p2 = findNode(root1, 2);
        TreeNode q2 = findNode(root1, 4);
        TreeNode lca2 = sol.lowestCommonAncestor(root1, p2, q2);
        System.out.println("Example 2 LCA: " + (lca2 != null ? lca2.val : "null")); // 2

        // Example 3: root = [2,1], p=2, q=1 → LCA=2
        TreeNode root3 = buildTree(new Integer[]{2, 1});
        TreeNode p3 = findNode(root3, 2);
        TreeNode q3 = findNode(root3, 1);
        TreeNode lca3 = sol.lowestCommonAncestor(root3, p3, q3);
        System.out.println("Example 3 LCA: " + (lca3 != null ? lca3.val : "null")); // 2
    }
}