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
    public int[] findPredecessorSuccessor(TreeNode root, int key) {
        int pred = -1, succ = -1;
        TreeNode curr = root;
        while (curr != null) {
            if (key < curr.val) {
                succ = curr.val;          // current node is a potential successor
                curr = curr.left;
            } else if (key > curr.val) {
                pred = curr.val;          // current node is a potential predecessor
                curr = curr.right;
            } else { // key == curr.val
                // Predecessor: maximum value in the left subtree (if any)
                if (curr.left != null) {
                    TreeNode temp = curr.left;
                    while (temp.right != null) temp = temp.right;
                    pred = temp.val;
                }
                // Successor: minimum value in the right subtree (if any)
                if (curr.right != null) {
                    TreeNode temp = curr.right;
                    while (temp.left != null) temp = temp.left;
                    succ = temp.val;
                }
                break;
            }
        }
        return new int[]{pred, succ};
    }
}

public class Main {
    // Build a BST from a level-order array (null for missing children)
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

        // Example 1: root = [5,2,10,1,4,7,12], key = 10 → output [7, 12]
        Integer[] arr1 = {5, 2, 10, 1, 4, 7, 12};
        TreeNode root1 = buildTree(arr1);
        int[] res1 = sol.findPredecessorSuccessor(root1, 10);
        System.out.println("Example 1 (key=10): [" + res1[0] + ", " + res1[1] + "]"); // [7, 12]

        // Example 2: same tree, key = 12 → output [10, -1]
        int[] res2 = sol.findPredecessorSuccessor(root1, 12);
        System.out.println("Example 2 (key=12): [" + res2[0] + ", " + res2[1] + "]"); // [10, -1]

        // Additional test: key = 1 → output [-1, 2]
        int[] res3 = sol.findPredecessorSuccessor(root1, 1);
        System.out.println("Additional test (key=1): [" + res3[0] + ", " + res3[1] + "]"); // [-1, 2]

        // Edge case: single node
        TreeNode root4 = buildTree(new Integer[]{7});
        int[] res4 = sol.findPredecessorSuccessor(root4, 7);
        System.out.println("Single node (key=7): [" + res4[0] + ", " + res4[1] + "]"); // [-1, -1]
    }
}