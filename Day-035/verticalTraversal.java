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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Map: column -> list of (row, value)
        Map<Integer, List<int[]>> colMap = new HashMap<>();
        // BFS: queue of (node, row, col)
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, 0, 0});

        int minCol = 0, maxCol = 0;

        while (!queue.isEmpty()) {
            Object[] cur = queue.poll();
            TreeNode node = (TreeNode) cur[0];
            int row = (int) cur[1];
            int col = (int) cur[2];

            colMap.computeIfAbsent(col, k -> new ArrayList<>()).add(new int[]{row, node.val});
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);

            if (node.left != null) {
                queue.offer(new Object[]{node.left, row + 1, col - 1});
            }
            if (node.right != null) {
                queue.offer(new Object[]{node.right, row + 1, col + 1});
            }
        }

        // Iterate columns from left to right
        for (int col = minCol; col <= maxCol; col++) {
            List<int[]> nodes = colMap.get(col);
            // Sort by row, then by value
            nodes.sort((a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });
            List<Integer> colVals = new ArrayList<>();
            for (int[] p : nodes) {
                colVals.add(p[1]);
            }
            result.add(colVals);
        }
        return result;
    }

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
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [3,9,20,null,null,15,7] -> [[9],[3,15],[20],[7]]
        TreeNode root1 = Solution.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(sol.verticalTraversal(root1));

        // Example 2: [1,2,3,4,5,6,7] -> [[4],[2],[1,5,6],[3],[7]]
        TreeNode root2 = Solution.buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(sol.verticalTraversal(root2));

        // Example 3: [1,2,3,4,6,5,7] -> [[4],[2],[1,5,6],[3],[7]]
        TreeNode root3 = Solution.buildTree(new Integer[]{1, 2, 3, 4, 6, 5, 7});
        System.out.println(sol.verticalTraversal(root3));
    }
}