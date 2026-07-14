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
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                // Find the rightmost node of the left subtree
                TreeNode runner = curr.left;
                while (runner.right != null) {
                    runner = runner.right;
                }
                // Connect the rightmost node to the current right subtree
                runner.right = curr.right;
                // Move the left subtree to the right and set left to null
                curr.right = curr.left;
                curr.left = null;
            }
            // Move to the next node in the flattened list
            curr = curr.right;
        }
    }
}

public class Main {
    // Helper to build a tree from level-order array (null for missing)
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

    // Helper to convert flattened tree to a list in preorder order
    public static List<Integer> flattenToList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,2,5,3,4,null,6]
        TreeNode root1 = buildTree(new Integer[]{1, 2, 5, 3, 4, null, 6});
        sol.flatten(root1);
        System.out.println("Example 1: " + flattenToList(root1));
        // Expected: [1, 2, 3, 4, 5, 6]

        // Example 2: []
        TreeNode root2 = buildTree(new Integer[]{});
        sol.flatten(root2);
        System.out.println("Example 2: " + flattenToList(root2)); // []

        // Example 3: [0]
        TreeNode root3 = buildTree(new Integer[]{0});
        sol.flatten(root3);
        System.out.println("Example 3: " + flattenToList(root3)); // [0]
    }
}