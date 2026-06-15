class Solution {
    public int maxMeetings(int[] start, int[] end) {
        int n = start.length;
        Meeting[] meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            meetings[i] = new Meeting(start[i], end[i]);
        }
        Arrays.sort(meetings, Comparator.comparingInt(m -> m.end));
        
        int count = 0;
        int lastEnd = -1;
        for (Meeting m : meetings) {
            if (m.start > lastEnd) {
                count++;
                lastEnd = m.end;
            }
        }
        return count;
    }
    
    class Meeting {
        int start;
        int end;
        Meeting(int s, int e) {
            start = s;
            end = e;
        }
    }
}