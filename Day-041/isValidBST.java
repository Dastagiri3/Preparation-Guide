import java.util.*;

class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long lower, long upper) {
        if (node == null)
            return true;
        if (node.val <= lower || node.val >= upper)
            return false;
        return validate(node.left, lower, node.val) && validate(node.right, node.val, upper);
    }
}

// Main class for testing (assumes TreeNode is provided by the platform)
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [2,1,3] → true
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("Example 1: " + sol.isValidBST(root1)); // true

        // Example 2: [5,1,4,null,null,3,6] → false
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("Example 2: " + sol.isValidBST(root2)); // false

        // Edge case: single node → true
        TreeNode root3 = new TreeNode(1);
        System.out.println("Single node: " + sol.isValidBST(root3)); // true
    }
}