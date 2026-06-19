import java.util.*;

class Solution {
    public List<String> findPath(int[][] mat) {
        List<String> result = new ArrayList<>();
        int n = mat.length;
        // If starting cell is blocked, no path exists
        if (n == 0 || mat[0][0] == 0) return result;
        
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        backtrack(mat, n, 0, 0, "", visited, result);
        return result;
    }

    private void backtrack(int[][] mat, int n, int row, int col,
                           String path, boolean[][] visited, List<String> result) {
        // If we reached the destination
        if (row == n - 1 && col == n - 1) {
            result.add(path);
            return;
        }

        // Move Down (row+1, col)
        if (row + 1 < n && mat[row + 1][col] == 1 && !visited[row + 1][col]) {
            visited[row + 1][col] = true;
            backtrack(mat, n, row + 1, col, path + 'D', visited, result);
            visited[row + 1][col] = false;
        }

        // Move Left (row, col-1)
        if (col - 1 >= 0 && mat[row][col - 1] == 1 && !visited[row][col - 1]) {
            visited[row][col - 1] = true;
            backtrack(mat, n, row, col - 1, path + 'L', visited, result);
            visited[row][col - 1] = false;
        }

        // Move Right (row, col+1)
        if (col + 1 < n && mat[row][col + 1] == 1 && !visited[row][col + 1]) {
            visited[row][col + 1] = true;
            backtrack(mat, n, row, col + 1, path + 'R', visited, result);
            visited[row][col + 1] = false;
        }

        // Move Up (row-1, col)
        if (row - 1 >= 0 && mat[row - 1][col] == 1 && !visited[row - 1][col]) {
            visited[row - 1][col] = true;
            backtrack(mat, n, row - 1, col, path + 'U', visited, result);
            visited[row - 1][col] = false;
        }
    }
}