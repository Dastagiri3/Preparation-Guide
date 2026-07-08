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
    private int preIndex = 0;
    private Map<Integer, Integer> inMap = new HashMap<>();
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Store the index of each value in inorder for O(1) lookup
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }
    
    private TreeNode build(int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        
        // The current root is the next element in preorder
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        
        // Find the root's position in inorder
        int inIndex = inMap.get(rootVal);
        
        // Build left and right subtrees recursively
        root.left = build(preorder, inStart, inIndex - 1);
        root.right = build(preorder, inIndex + 1, inEnd);
        
        return root;
    }
}

public class Main {
    // Helper to convert the tree into a level‑order list (with nulls for missing children)
    public static List<Integer> treeToList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add(null);
                continue;
            }
            result.add(node.val);
            queue.offer(node.left);
            queue.offer(node.right);
        }
        
        // Remove trailing nulls for cleaner output
        int i = result.size() - 1;
        while (i >= 0 && result.get(i) == null) {
            result.remove(i);
            i--;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        TreeNode root1 = sol.buildTree(preorder1, inorder1);
        System.out.println("Example 1: " + treeToList(root1));
        // Output: [3, 9, 20, null, null, 15, 7]

        // Example 2
        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        TreeNode root2 = sol.buildTree(preorder2, inorder2);
        System.out.println("Example 2: " + treeToList(root2));
        // Output: [-1]
    }
}