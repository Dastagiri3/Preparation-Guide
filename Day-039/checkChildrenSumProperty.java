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
    public boolean checkChildrenSumProperty(TreeNode root) {
        // Base case: empty tree or leaf node satisfies the property
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        
        int leftVal = (root.left != null) ? root.left.val : 0;
        int rightVal = (root.right != null) ? root.right.val : 0;
        
        // Check the current node's property and recursively check subtrees
        return (root.val == leftVal + rightVal)
                && checkChildrenSumProperty(root.left)
                && checkChildrenSumProperty(root.right);
    }
}

public class Main {
    // Helper to build a tree from a level-order array (null for missing nodes)
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

        // Example 1: [1,4,3,5] → false
        TreeNode root1 = buildTree(new Integer[]{1, 4, 3, 5});
        System.out.println("Example 1: " + sol.checkChildrenSumProperty(root1)); // false

        // Example 2: [10,4,6,1,3,2,4] → true
        TreeNode root2 = buildTree(new Integer[]{10, 4, 6, 1, 3, 2, 4});
        System.out.println("Example 2: " + sol.checkChildrenSumProperty(root2)); // true

        // Additional test: single node → true
        TreeNode root3 = buildTree(new Integer[]{5});
        System.out.println("Single node: " + sol.checkChildrenSumProperty(root3)); // true

        // Edge case: empty tree → true
        System.out.println("Empty tree: " + sol.checkChildrenSumProperty(null)); // true
    }
}