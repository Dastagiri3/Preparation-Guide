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
    // Recursive preorder traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    // Iterative version (uncomment if needed)
    /*
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.add(curr.val);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return result;
    }
    */
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,null,2,3] -> [1,2,3]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        System.out.println(sol.preorderTraversal(root1)); // [1, 2, 3]

        // Example 2: [1,2,3,4,5,null,8,null,null,6,7,9] -> [1,2,4,5,6,7,3,8,9]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(8);
        root2.left.right.left = new TreeNode(6);
        root2.left.right.right = new TreeNode(7);
        root2.right.right.left = new TreeNode(9);
        System.out.println(sol.preorderTraversal(root2));

        // Example 3: [] -> []
        System.out.println(sol.preorderTraversal(null));

        // Example 4: [1] -> [1]
        TreeNode root4 = new TreeNode(1);
        System.out.println(sol.preorderTraversal(root4));
    }
}