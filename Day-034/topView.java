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
    public List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Map: horizontal distance -> node value (first encountered)
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode node = p.node;
            int hd = p.hd;

            // If this horizontal distance is not yet in the map,
            // this is the first (topmost) node for this distance.
            if (!map.containsKey(hd)) {
                map.put(hd, node.val);
            }

            if (node.left != null) queue.offer(new Pair(node.left, hd - 1));
            if (node.right != null) queue.offer(new Pair(node.right, hd + 1));
        }

        // The TreeMap keeps keys (horizontal distances) sorted.
        result.addAll(map.values());
        return result;
    }

    // Helper class to store node and its horizontal distance
    private static class Pair {
        TreeNode node;
        int hd;
        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,2,3,4,5,6,7] -> expected [4,2,1,3,7]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        System.out.println(sol.topView(root1)); // [4, 2, 1, 3, 7]

        // Example 2: [10,20,30,40,60,90,100] -> expected [40,20,10,30,100]
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(20);
        root2.right = new TreeNode(30);
        root2.left.left = new TreeNode(40);
        root2.left.right = new TreeNode(60);
        root2.right.left = new TreeNode(90);
        root2.right.right = new TreeNode(100);
        System.out.println(sol.topView(root2)); // [40, 20, 10, 30, 100]

        // "Now your turn!": [5,1,2,8,null,4,5,null,6] -> expected [8,1,5,2,5]? 
        // Let's build the tree:
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(8);
        root3.right.left = new TreeNode(4);
        root3.right.right = new TreeNode(5);
        root3.left.left.right = new TreeNode(6);
        System.out.println(sol.topView(root3)); // [8, 1, 5, 2, 5]  (the correct answer from the choices)
    }
}