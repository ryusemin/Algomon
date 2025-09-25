import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String temp = "";

        for (int i = 0; temp.length() <= t * m; i++) {
            temp += Integer.toString(i, n);
        }

        for (int i = p-1; answer.length() < t; i += m) {
            answer += temp.charAt(i);
        }

        return answer.toUpperCase();
    }
}