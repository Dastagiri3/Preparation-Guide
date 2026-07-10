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
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }
}

public class Main {
    // Helper to print tree in level-order (with null placeholders)
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
        // Remove trailing nulls
        int i = result.size() - 1;
        while (i >= 0 && result.get(i) == null) {
            result.remove(i);
            i--;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: nums = [-10, -3, 0, 5, 9]
        int[] nums1 = { -10, -3, 0, 5, 9 };
        TreeNode root1 = sol.sortedArrayToBST(nums1);
        System.out.println("Example 1: " + treeToList(root1));
        // Possible output: [0, -3, 9, -10, null, 5] or [0, -10, 5, null, -3, null, 9]

        // Example 2: nums = [1, 3]
        int[] nums2 = { 1, 3 };
        TreeNode root2 = sol.sortedArrayToBST(nums2);
        System.out.println("Example 2: " + treeToList(root2));
        // Possible output: [3, 1] or [1, null, 3] (both acceptable)
    }
}