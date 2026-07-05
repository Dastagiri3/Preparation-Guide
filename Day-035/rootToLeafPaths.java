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
    public List<List<Integer>> rootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> current, List<List<Integer>> result) {
        current.add(node.val);
        // If leaf node, add the current path to result
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(current));
        } else {
            if (node.left != null) dfs(node.left, current, result);
            if (node.right != null) dfs(node.right, current, result);
        }
        // backtrack
        current.remove(current.size() - 1);
    }

    // Helper to build a binary tree from level-order array (null for missing nodes)
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

        // Example 1: [1,2,3,null,5,null,4] -> [[1,2,5],[1,3,4]]
        TreeNode root1 = Solution.buildTree(new Integer[]{1, 2, 3, null, 5, null, 4});
        System.out.println("Example 1: " + sol.rootToLeafPaths(root1));

        // Example 2: [1,2,3,4,5] -> [[1,2,4],[1,2,5],[1,3]]
        TreeNode root2 = Solution.buildTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("Example 2: " + sol.rootToLeafPaths(root2));

        // "Now your turn!": [1,2,3,4,null,5,6,null,7]
        // Tree structure:
        //         1
        //       /   \
        //      2     3
        //     /     / \
        //    4     5   6
        //     \
        //      7
        TreeNode root3 = Solution.buildTree(new Integer[]{1, 2, 3, 4, null, 5, 6, null, 7});
        System.out.println("Now your turn: " + sol.rootToLeafPaths(root3));
        // Expected: [[1,2,4,7], [1,3,5], [1,3,6]]  (from the options, the correct one is the first)
    }
}