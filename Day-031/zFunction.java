class Solution {
    // Computes the Z-array for a given string s
    public int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n];
        int l = 0, r = 0; // [l, r] is the current Z-box (rightmost matched segment)
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                // We are inside the Z-box: use previously computed z[i-l] but cap by r-i+1
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            // Extend the match as far as possible
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            // Update the Z-box if this match extends further right
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        // z[0] is conventionally set to 0 or n; we set to 0 (common practice)
        z[0] = 0; // or could be n, but typically 0 for pattern matching
        return z;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] testStrings = {
            "aaaaaa",
            "abcabca",
            "abababab",
            "aabxaabxcaabxaabx"
        };
        for (String s : testStrings) {
            int[] z = sol.zFunction(s);
            System.out.print("Z-array for \"" + s + "\": ");
            for (int val : z) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}