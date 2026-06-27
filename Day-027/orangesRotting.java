import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        
        // Initialize queue with all rotten oranges and count fresh
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        
        // If no fresh oranges, return 0
        if (fresh == 0) return 0;
        
        int minutes = 0;
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rotted = false;
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int r = cell[0], c = cell[1];
                for (int[] d : directions) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        queue.offer(new int[]{nr, nc});
                        rotted = true;
                    }
                }
            }
            if (rotted) minutes++;
        }
        
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[][] grid1 = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(sol.orangesRotting(grid1)); // 4

        // Example 2
        int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(sol.orangesRotting(grid2)); // -1

        // Example 3
        int[][] grid3 = {{0,2}};
        System.out.println(sol.orangesRotting(grid3)); // 0
    }
}