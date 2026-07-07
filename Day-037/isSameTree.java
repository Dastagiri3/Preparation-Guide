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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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

        // Example 1: p = [1,2,3], q = [1,2,3] -> true
        TreeNode p1 = Solution.buildTree(new Integer[]{1, 2, 3});
        TreeNode q1 = Solution.buildTree(new Integer[]{1, 2, 3});
        System.out.println("Example 1: " + sol.isSameTree(p1, q1)); // true

        // Example 2: p = [1,2], q = [1,null,2] -> false
        TreeNode p2 = Solution.buildTree(new Integer[]{1, 2});
        TreeNode q2 = Solution.buildTree(new Integer[]{1, null, 2});
        System.out.println("Example 2: " + sol.isSameTree(p2, q2)); // false

        // Example 3: p = [1,2,1], q = [1,1,2] -> false
        TreeNode p3 = Solution.buildTree(new Integer[]{1, 2, 1});
        TreeNode q3 = Solution.buildTree(new Integer[]{1, 1, 2});
        System.out.println("Example 3: " + sol.isSameTree(p3, q3)); // false

        // Edge case: both empty -> true
        System.out.println("Both empty: " + sol.isSameTree(null, null)); // true
    }
}