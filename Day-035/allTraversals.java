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
    public List<List<Integer>> allTraversals(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        if (root == null) {
            return Arrays.asList(in, pre, post); // all empty
        }

        // Stack of pairs: (node, state) where state=0 -> pre, 1 -> in, 2 -> post
        Deque<Object[]> stack = new ArrayDeque<>();
        stack.push(new Object[]{root, 0});

        while (!stack.isEmpty()) {
            Object[] cur = stack.pop();
            TreeNode node = (TreeNode) cur[0];
            int state = (int) cur[1];

            if (state == 0) {
                // Pre-order: visit node, then left, then right
                pre.add(node.val);
                // Push right first so left is processed next (LIFO)
                if (node.right != null) stack.push(new Object[]{node.right, 0});
                if (node.left != null) stack.push(new Object[]{node.left, 0});
            } else if (state == 1) {
                // In-order: left, node, right
                in.add(node.val);
            } else if (state == 2) {
                // Post-order: left, right, node
                post.add(node.val);
            }
        }

        return Arrays.asList(in, pre, post);
    }

    // Helper to build a binary tree from a level-order array (null for missing nodes)
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

        // Example 1: [1,3,4,5,2,7,6]
        TreeNode root1 = Solution.buildTree(new Integer[]{1, 3, 4, 5, 2, 7, 6});
        List<List<Integer>> result1 = sol.allTraversals(root1);
        System.out.println("Inorder:   " + result1.get(0));
        System.out.println("Preorder:  " + result1.get(1));
        System.out.println("Postorder: " + result1.get(2));
        // Expected: Inorder [5,3,2,1,7,4,6], Preorder [1,3,5,2,4,7,6], Postorder [5,2,3,7,6,4,1]

        System.out.println();

        // Example 2: [1,2,3,null,null,null,6]
        TreeNode root2 = Solution.buildTree(new Integer[]{1, 2, 3, null, null, null, 6});
        List<List<Integer>> result2 = sol.allTraversals(root2);
        System.out.println("Inorder:   " + result2.get(0));
        System.out.println("Preorder:  " + result2.get(1));
        System.out.println("Postorder: " + result2.get(2));
        // Expected: Inorder [2,1,3,6], Preorder [1,2,3,6], Postorder [2,6,3,1]

        System.out.println();

        // "Now your turn!": [5,1,2,8,null,4,5,null,6]
        TreeNode root3 = Solution.buildTree(new Integer[]{5, 1, 2, 8, null, 4, 5, null, 6});
        List<List<Integer>> result3 = sol.allTraversals(root3);
        System.out.println("Inorder:   " + result3.get(0));
        System.out.println("Preorder:  " + result3.get(1));
        System.out.println("Postorder: " + result3.get(2));
        // Expected: Inorder [8,6,1,5,4,2,5], Preorder [5,1,8,6,2,4,5], Postorder [6,8,1,4,5,2,5]
    }
}