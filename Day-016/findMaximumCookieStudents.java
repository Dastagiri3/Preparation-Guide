import java.util.Arrays;

class Solution {
    public int findMaximumCookieStudents(int[] Student, int[] Cookie) {
        Arrays.sort(Student);
        Arrays.sort(Cookie);
        
        int i = 0; 
        int j = 0; 
        int count = 0;
        
        while (i < Student.length && j < Cookie.length) {
            if (Cookie[j] >= Student[i]) {
                count++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return count;
    }
}