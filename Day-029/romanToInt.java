import java.util.*;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int curr = map.get(s.charAt(i));
            if (i < n - 1 && curr < map.get(s.charAt(i + 1))) {
                result -= curr;
            } else {
                result += curr;
            }
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.romanToInt("III"));     // 3

        // Example 2
        System.out.println(sol.romanToInt("LVIII"));   // 58

        // Example 3
        System.out.println(sol.romanToInt("MCMXCIV")); // 1994
    }
}