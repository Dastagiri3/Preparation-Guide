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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // The last node in this level will be the rightmost visible
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                if (i == levelSize - 1) {
                    result.add(curr.val);
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,2,3,null,5,null,4] -> [1,3,4]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        System.out.println(sol.rightSideView(root1)); // [1, 3, 4]

        // Example 2: [1,2,3,4,null,null,null,5] -> [1,3,4,5]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.left.left = new TreeNode(5); // note: the example shows 5 as left child of 4? Actually description says [1,2,3,4,null,null,null,5] which would be level order: 1,2,3,4,null,null,null,5. That means 5 is left child of 4? Let's build accordingly.
        // Correct construction for the given level order:
        // level 0: 1
        // level 1: 2, 3
        // level 2: 4, null, null, null
        // level 3: 5 as left child of 4? But level order array has 5 at index 7, which is left child of node at index 3 (which is 4). So we do:
        // Actually to match exactly, we'll build:
        root2.left.left = new TreeNode(4);
        root2.left.left.left = new TreeNode(5);
        System.out.println(sol.rightSideView(root2)); // [1, 3, 4, 5]
    }
}