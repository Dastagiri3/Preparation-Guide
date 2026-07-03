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
    // Recursive postorder traversal: left -> right -> root
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }

    // Iterative alternative (using two stacks or one stack with reverse)
    /*
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            output.push(curr);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        while (!output.isEmpty()) {
            result.add(output.pop().val);
        }
        return result;
    }
    */
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,null,2,3] -> [3,2,1]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        System.out.println(sol.postorderTraversal(root1)); // [3, 2, 1]

        // Example 2: complex tree -> expected [4,6,7,5,2,9,8,3,1]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(8);
        root2.left.right.left = new TreeNode(6);
        root2.left.right.right = new TreeNode(7);
        root2.right.right.left = new TreeNode(9);
        System.out.println(sol.postorderTraversal(root2)); 
        // Expected: [4, 6, 7, 5, 2, 9, 8, 3, 1]

        // Example 3: empty -> []
        System.out.println(sol.postorderTraversal(null));

        // Example 4: single node -> [1]
        TreeNode root4 = new TreeNode(1);
        System.out.println(sol.postorderTraversal(root4));
    }
}