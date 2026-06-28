import java.util.*;

// Non-public Solution class
class Solution {
    public int findCelebrity(int[][] M, int n) {
        int candidate = 0;
        // Find a potential celebrity candidate
        for (int i = 1; i < n; i++) {
            // If candidate knows i, candidate cannot be celebrity, so candidate = i
            // Otherwise, i cannot be celebrity (because candidate doesn't know i)
            if (M[candidate][i] == 1) {
                candidate = i;
            }
        }
        // Verify the candidate
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            // Candidate must not know anyone, and everyone must know candidate
            if (M[candidate][i] == 1 || M[i][candidate] == 0) {
                return -1;
            }
        }
        return candidate;
    }
}

// Public Main class with entry point
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[][] M1 = {
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {0, 1, 1, 0}
        };
        System.out.println(sol.findCelebrity(M1, 4)); // 1

        // Example 2
        int[][] M2 = {{0, 1}, {1, 0}};
        System.out.println(sol.findCelebrity(M2, 2)); // -1
    }
}