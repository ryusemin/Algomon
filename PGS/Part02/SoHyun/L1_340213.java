import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        int v = Integer.parseInt(video_len.split(":")[0])
                * 60 + Integer.parseInt(video_len.split(":")[1]);
        int p = Integer.parseInt(pos.split(":")[0])
                * 60 + Integer.parseInt(pos.split(":")[1]);
        int ops = Integer.parseInt(op_start.split(":")[0])
                * 60 + Integer.parseInt(op_start.split(":")[1]);
        int ope = Integer.parseInt(op_end.split(":")[0])
                * 60 + Integer.parseInt(op_end.split(":")[1]);

        if (p >= ops && p <= ope) {
            p = ope;
        }

        for (String c : commands) {
            if (c.equals("prev")) {
                if (p < 10) {
                    p = 0;
                } else {
                    p -= 10;
                }
            } else {
                if (v - p < 10) {
                    p = v;
                } else {
                    p += 10;
                }
            }

            if (p >= ops && p <= ope) {
                p = ope;
            }
        }

        String answer = String.format("%02d:%02d", p/60, p%60);
        return answer;
    }
}