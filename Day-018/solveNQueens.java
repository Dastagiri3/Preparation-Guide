class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n]; // queens[row] = column
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // row - col + (n-1) for 0-index
        boolean[] diag2 = new boolean[2 * n - 1]; // row + col
        backtrack(result, queens, cols, diag1, diag2, 0, n);
        return result;
    }

    private void backtrack(List<List<String>> result, int[] queens,
                           boolean[] cols, boolean[] diag1, boolean[] diag2,
                           int row, int n) {
        if (row == n) {
            result.add(constructBoard(queens, n));
            return;
        }
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;
            if (cols[col] || diag1[d1] || diag2[d2]) continue;
            queens[row] = col;
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            backtrack(result, queens, cols, diag1, diag2, row + 1, n);
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    private List<String> constructBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.');
            rowChars[queens[row]] = 'Q';
            board.add(new String(rowChars));
        }
        return board;
    }
}