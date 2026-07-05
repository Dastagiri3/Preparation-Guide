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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;
        // Queue stores (node, index) where index is the position in a complete binary tree
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0L));
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long firstIndex = queue.peek().index;
            long lastIndex = firstIndex;
            for (int i = 0; i < levelSize; i++) {
                Pair p = queue.poll();
                TreeNode node = p.node;
                long idx = p.index;
                if (i == levelSize - 1) lastIndex = idx;
                if (node.left != null) {
                    queue.offer(new Pair(node.left, 2 * idx + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, 2 * idx + 2));
                }
            }
            maxWidth = (int) Math.max(maxWidth, lastIndex - firstIndex + 1);
        }
        return maxWidth;
    }
    
    // Helper class to store node and its index
    private static class Pair {
        TreeNode node;
        long index;
        Pair(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }
}

public class Main {
    // Helper to build a binary tree from a level-order array (null for missing)
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

        // Example 1: [1,3,2,5,3,null,9] -> expected 4
        TreeNode root1 = buildTree(new Integer[]{1, 3, 2, 5, 3, null, 9});
        System.out.println("Example 1: " + sol.widthOfBinaryTree(root1)); // 4

        // Example 2: [1,3,2,5,null,null,9,6,null,7] -> expected 7
        TreeNode root2 = buildTree(new Integer[]{1, 3, 2, 5, null, null, 9, 6, null, 7});
        System.out.println("Example 2: " + sol.widthOfBinaryTree(root2)); // 7

        // Example 3: [1,3,2,5] -> expected 2
        TreeNode root3 = buildTree(new Integer[]{1, 3, 2, 5});
        System.out.println("Example 3: " + sol.widthOfBinaryTree(root3)); // 2
    }
}