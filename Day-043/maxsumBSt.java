import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    /**
     * Returns an array of 4 integers:
     * [0] -> 1 if subtree is BST, 0 otherwise
     * [1] -> sum of subtree (if BST, else 0)
     * [2] -> minimum value in subtree (if BST, else 0)
     * [3] -> maximum value in subtree (if BST, else 0)
     */
    private int[] dfs(TreeNode node) {
        if (node == null) {
            // null subtree is a valid BST with sum 0, min = +inf, max = -inf
            return new int[] { 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE };
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Check BST property
        boolean leftValid = left[0] == 1 && (node.left == null || left[3] < node.val);
        boolean rightValid = right[0] == 1 && (node.right == null || right[2] > node.val);

        if (leftValid && rightValid) {
            int sum = node.val + left[1] + right[1];
            maxSum = Math.max(maxSum, sum);

            int min = Math.min(node.val, left[2]);
            int max = Math.max(node.val, right[3]);
            return new int[] { 1, sum, min, max };
        } else {
            return new int[] { 0, 0, 0, 0 };
        }
    }
}

public class maxSumBST {
    // Helper to build a tree from a level‑order array (null for missing nodes)
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

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,4,3,2,4,2,5,null,null,null,null,null,4,6] → expected 20
        Integer[] arr1 = { 1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, 4, 6 };
        TreeNode root1 = buildTree(arr1);
        System.out.println("Example 1: " + sol.maxSumBST(root1)); // 20

        // Example 2: [4,3,null,1,2] → expected 2
        Integer[] arr2 = { 4, 3, null, 1, 2 };
        TreeNode root2 = buildTree(arr2);
        System.out.println("Example 2: " + sol.maxSumBST(root2)); // 2

        // Example 3: [-4,-2,-5] → expected 0
        Integer[] arr3 = { -4, -2, -5 };
        TreeNode root3 = buildTree(arr3);
        System.out.println("Example 3: " + sol.maxSumBST(root3)); // 0

        // Additional test: valid BST with positive sum
        TreeNode root4 = new TreeNode(2);
        root4.left = new TreeNode(1);
        root4.right = new TreeNode(3);
        System.out.println("Valid BST sum: " + sol.maxSumBST(root4)); // 6
    }
}