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
    private int postIndex;
    private Map<Integer, Integer> inMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Build a map: value -> its index in inorder
        inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        postIndex = postorder.length - 1; // start from the end of postorder
        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        // The current root is the last element of the current postorder segment
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        // Find the root's position in inorder
        int inIndex = inMap.get(rootVal);

        // Build right subtree first because postorder goes left → right → root,
        // so the next element before root is from the right subtree.
        root.right = build(inorder, postorder, inIndex + 1, inEnd);
        root.left = build(inorder, postorder, inStart, inIndex - 1);

        return root;
    }
}

public class Main {
    // Helper: convert tree to a level‑order list (with null for missing children)
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
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};
        TreeNode root1 = sol.buildTree(inorder1, postorder1);
        System.out.println("Example 1: " + treeToList(root1));
        // Expected: [3, 9, 20, null, null, 15, 7]

        // Example 2
        int[] inorder2 = {-1};
        int[] postorder2 = {-1};
        TreeNode root2 = sol.buildTree(inorder2, postorder2);
        System.out.println("Example 2: " + treeToList(root2));
        // Expected: [-1]
    }
}