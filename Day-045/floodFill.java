import java.util.*;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        // If the new color is the same as the original, no changes needed
        if (original == color) return image;
        dfs(image, sr, sc, original, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int original, int newColor) {
        // Boundary checks and color match
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != original) {
            return;
        }
        image[r][c] = newColor;
        // Explore 4-directionally
        dfs(image, r - 1, c, original, newColor);
        dfs(image, r + 1, c, original, newColor);
        dfs(image, r, c - 1, original, newColor);
        dfs(image, r, c + 1, original, newColor);
    }

    // Helper to print the image in a readable format
    private static void printImage(int[][] image) {
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: image = [[1,1,1],[1,1,0],[1,0,1]], sr=1, sc=1, color=2
        int[][] image1 = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int[][] result1 = sol.floodFill(image1, 1, 1, 2);
        System.out.println("Example 1 Output:");
        printImage(result1);
        // Expected: [[2,2,2],[2,2,0],[2,0,1]]

        System.out.println();

        // Example 2: image = [[0,0,0],[0,0,0]], sr=0, sc=0, color=0
        int[][] image2 = {
            {0, 0, 0},
            {0, 0, 0}
        };
        int[][] result2 = sol.floodFill(image2, 0, 0, 0);
        System.out.println("Example 2 Output:");
        printImage(result2);
        // Expected: [[0,0,0],[0,0,0]] (no change because original color == new color)
    }
}