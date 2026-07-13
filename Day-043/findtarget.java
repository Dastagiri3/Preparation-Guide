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
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }

    private boolean dfs(TreeNode node, int k, Set<Integer> set) {
        if (node == null)
            return false;
        // Check if complement exists in the set
        if (set.contains(k - node.val))
            return true;
        // Add current value to set
        set.add(node.val);
        // Recurse left and right
        return dfs(node.left, k, set) || dfs(node.right, k, set);
    }
}

public class findTarget {
    // Helper: build a binary tree from a level‑order array (null for missing)
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

        // Example 1: root = [5,3,6,2,4,null,7], k = 9 → true
        Integer[] arr1 = { 5, 3, 6, 2, 4, null, 7 };
        TreeNode root1 = buildTree(arr1);
        System.out.println("Example 1 (k=9): " + sol.findTarget(root1, 9)); // true

        // Example 2: same tree, k = 28 → false
        System.out.println("Example 2 (k=28): " + sol.findTarget(root1, 28)); // false

        // Additional test: single node, k = node.val (no pair) → false
        TreeNode root2 = new TreeNode(1);
        System.out.println("Single node (k=2): " + sol.findTarget(root2, 2)); // false
    }
}