import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        // Remove trailing comma and close bracket
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0].trim()));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < vals.length) {
            TreeNode curr = queue.poll();
            // Left child
            if (i < vals.length && !vals[i].trim().equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(vals[i].trim()));
                queue.offer(curr.left);
            }
            i++;
            // Right child
            if (i < vals.length && !vals[i].trim().equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(vals[i].trim()));
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    // Helper: print tree in level-order (for verification)
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add("null");
                continue;
            }
            result.add(String.valueOf(node.val));
            queue.offer(node.left);
            queue.offer(node.right);
        }
        // Remove trailing nulls
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i);
            i--;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        Codec codec = new Codec();

        // Example 1: root = [1,2,3,null,null,4,5]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        String serialized = codec.serialize(root1);
        System.out.println("Serialized: " + serialized);
        // Expected: [1,2,3,null,null,4,5]

        TreeNode deserialized = codec.deserialize(serialized);
        System.out.print("Deserialized (level-order): ");
        printLevelOrder(deserialized);
        // Should match the original

        // Example 2: empty tree
        TreeNode root2 = null;
        String serialized2 = codec.serialize(root2);
        System.out.println("Serialized empty: " + serialized2); // []
        TreeNode deserialized2 = codec.deserialize(serialized2);
        System.out.print("Deserialized empty: ");
        printLevelOrder(deserialized2); // []
    }
}