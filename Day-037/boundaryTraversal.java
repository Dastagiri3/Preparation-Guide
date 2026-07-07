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
    public List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Add root (if it's a leaf, we add it only once)
        if (!isLeaf(root)) {
            result.add(root.val);
        }

        // Add left boundary (excluding leaves)
        addLeftBoundary(root.left, result);

        // Add all leaves (left to right)
        addLeaves(root, result);

        // Add right boundary in reverse order (excluding leaves)
        addRightBoundary(root.right, result);

        return result;
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private void addLeftBoundary(TreeNode node, List<Integer> result) {
        while (node != null) {
            if (!isLeaf(node)) {
                result.add(node.val);
            }
            // Move to left child if exists, otherwise to right child
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    private void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) return;
        if (isLeaf(node)) {
            result.add(node.val);
            return;
        }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    private void addRightBoundary(TreeNode node, List<Integer> result) {
        List<Integer> temp = new ArrayList<>();
        while (node != null) {
            if (!isLeaf(node)) {
                temp.add(node.val);
            }
            // Move to right child if exists, otherwise to left child
            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        // Reverse and add to result
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
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

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,2,3,4,5,6,7,null,null,8,9]
        Integer[] arr1 = {1, 2, 3, 4, 5, 6, 7, null, null, 8, 9};
        TreeNode root1 = buildTree(arr1);
        System.out.println("Example 1: " + sol.boundaryTraversal(root1));
        // Expected: [1, 2, 4, 8, 9, 6, 7, 3]

        // Example 2: [1,2,null,4,9,6,5,3,null,null,null,null,null,7,8]
        Integer[] arr2 = {1, 2, null, 4, 9, 6, 5, 3, null, null, null, null, null, 7, 8};
        TreeNode root2 = buildTree(arr2);
        System.out.println("Example 2: " + sol.boundaryTraversal(root2));
        // Expected: [1, 2, 4, 6, 5, 7, 8]

        // Edge case: single node
        TreeNode root3 = new TreeNode(10);
        System.out.println("Single node: " + sol.boundaryTraversal(root3));
        // Expected: [10]

        // Edge case: empty
        System.out.println("Empty: " + sol.boundaryTraversal(null));
    }
}