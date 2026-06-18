class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0, isPalindrome);
        return result;
    }

    private void backtrack(List<List<String>> result, List<String> current, String s, int start, boolean[][] isPalindrome) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome[start][end]) {
                current.add(s.substring(start, end + 1));
                backtrack(result, current, s, end + 1, isPalindrome);
                current.remove(current.size() - 1);
            }
        }
    }
}