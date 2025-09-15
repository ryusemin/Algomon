import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int len = stringToSecond(video_len);
        int now = stringToSecond(pos);
        int os = stringToSecond(op_start);
        int oe = stringToSecond(op_end);
        
        if (inOpening(os, oe, now)) 
            now = oe;
        
        for (String cmd : commands) {
            if ("prev".equals(cmd)) {
                now = Math.max(0, now - 10);
            } else {
                now = Math.min(len, now + 10);
            }
            if (inOpening(os, oe, now)) now = oe;
        }

        int m = now / 60;
        int s = now % 60;
        return String.format("%02d:%02d", m, s);
    }
    
    int stringToSecond(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
    
    boolean inOpening(int os, int oe, int now) {
        return os <= now && now <= oe;
    }
}