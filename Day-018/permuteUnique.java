class Solution {
    public List<String> permuteUnique(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars); // sort to get lexicographic order and handle duplicates
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[chars.length];
        backtrack(result, new StringBuilder(), chars, used);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, char[] chars, boolean[] used) {
        if (current.length() == chars.length) {
            result.add(current.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            // Skip if used or duplicate (skip duplicates to avoid duplicate permutations)
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            current.append(chars[i]);
            backtrack(result, current, chars, used);
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }
}
