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
    private int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        index = 0;
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Recursively build tree using allowed value range (lower, upper)
    private TreeNode build(int[] preorder, int lower, int upper) {
        if (index >= preorder.length)
            return null;
        int val = preorder[index];
        if (val < lower || val > upper)
            return null; // not valid for this subtree

        index++; // consume the current node
        TreeNode node = new TreeNode(val);
        node.left = build(preorder, lower, val); // left values must be < val
        node.right = build(preorder, val, upper); // right values must be > val
        return node;
    }
}

public class Main {
    // Helper: convert tree to level-order list with nulls for missing children
    public static List<Integer> treeToList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

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

        // Remove trailing nulls to match the expected output format
        int i = result.size() - 1;
        while (i >= 0 && result.get(i) == null) {
            result.remove(i);
            i--;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: preorder = [8,5,1,7,10,12] → expected [8,5,10,1,7,null,12]
        int[] pre1 = { 8, 5, 1, 7, 10, 12 };
        TreeNode root1 = sol.bstFromPreorder(pre1);
        System.out.println("Example 1: " + treeToList(root1));

        // Example 2: preorder = [1,3] → expected [1,null,3]
        int[] pre2 = { 1, 3 };
        TreeNode root2 = sol.bstFromPreorder(pre2);
        System.out.println("Example 2: " + treeToList(root2));
    }
}