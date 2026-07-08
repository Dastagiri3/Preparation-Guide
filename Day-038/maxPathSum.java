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
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    // Returns the maximum path sum starting from the current node going downward
    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Max gain from left and right children (ignore if negative)
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Path sum if this node is the highest node of the path
        int priceNewPath = node.val + leftGain + rightGain;

        // Update global max
        maxSum = Math.max(maxSum, priceNewPath);

        // Return the maximum gain from this node (only one branch can be extended)
        return node.val + Math.max(leftGain, rightGain);
    }

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
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,2,3] -> 6
        TreeNode root1 = Solution.buildTree(new Integer[]{1, 2, 3});
        System.out.println("Example 1: " + sol.maxPathSum(root1)); // 6

        // Example 2: [-10,9,20,null,null,15,7] -> 42
        TreeNode root2 = Solution.buildTree(new Integer[]{-10, 9, 20, null, null, 15, 7});
        System.out.println("Example 2: " + sol.maxPathSum(root2)); // 42

        // Additional test: negative values
        TreeNode root3 = Solution.buildTree(new Integer[]{-1, -2, -3});
        System.out.println("All negative: " + sol.maxPathSum(root3)); // -1 (max single node)
    }
}