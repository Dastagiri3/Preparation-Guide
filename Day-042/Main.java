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
    /**
     * Finds the floor and ceil values for a given key in a BST.
     *
     * @param root the root of the BST
     * @param key  the key to search for
     * @return an int array of two elements: [floor, ceil] (or -1 if missing)
     */
    public int[] findFloorCeil(TreeNode root, int key) {
        int floor = -1;
        int ceil = -1;
        TreeNode curr = root;
        while (curr != null) {
            if (key == curr.val) {
                floor = curr.val;
                ceil = curr.val;
                break;
            } else if (key < curr.val) {
                // current node is a candidate for ceil
                ceil = curr.val;
                curr = curr.left;
            } else { // key > curr.val
                // current node is a candidate for floor
                floor = curr.val;
                curr = curr.right;
            }
        }
        return new int[] { floor, ceil };
    }
}

public class Main {
    // Helper to build a BST from a level‑order array (null for missing children)
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null)
            return null;
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

        // Example 1: root = [8,4,12,2,6,10,14], key = 11 → expected [10, 12]
        Integer[] arr1 = { 8, 4, 12, 2, 6, 10, 14 };
        TreeNode root1 = buildTree(arr1);
        int[] res1 = sol.findFloorCeil(root1, 11);
        System.out.println("Example 1 (key=11): [" + res1[0] + ", " + res1[1] + "]"); // [10, 12]

        // Example 2: same tree, key = 15 → expected [14, -1]
        int[] res2 = sol.findFloorCeil(root1, 15);
        System.out.println("Example 2 (key=15): [" + res2[0] + ", " + res2[1] + "]"); // [14, -1]

        // Additional test: key = 6 (present) → floor=6, ceil=6
        int[] res3 = sol.findFloorCeil(root1, 6);
        System.out.println("Additional test (key=6): [" + res3[0] + ", " + res3[1] + "]"); // [6, 6]

        // Edge case: key smaller than all nodes → floor=-1, ceil=2 (smallest node)
        int[] res4 = sol.findFloorCeil(root1, 1);
        System.out.println("Edge test (key=1): [" + res4[0] + ", " + res4[1] + "]"); // [-1, 2]
    }
}