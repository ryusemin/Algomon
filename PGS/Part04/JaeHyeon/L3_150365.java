import java.util.*;

class Solution {
    int[] dr = new int[]{1, 0, 0, -1};
    int[] dc = new int[]{0, -1, 1, 0};
    char[] cmd = new char[]{'d', 'l', 'r', 'u'};
    boolean isFound = false;
    String answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        dfs(n, m, x - 1, y - 1, r - 1, c - 1, k, 0, new StringBuilder());
        
        return isFound ? answer : "impossible";
    }
    
    void dfs (int n, int m, int x, int y, int r, int c, int k, int depth, StringBuilder sb) {
        if (isFound)    return;
        
        int remain = k - depth;
        int dist   = Math.abs(x - r) + Math.abs(y - c);
        if (dist > remain || (remain - dist) % 2 == 1) return;
        
        if (depth == k) {
            if (x == r && y == c) {
                answer = sb.toString();
                isFound = true;
            }
            return;
        }
        
        for (int d = 0; d < 4; d++) {
            int nr = x + dr[d];
            int nc = y + dc[d];
            
            if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                sb.append(cmd[d]);
                dfs(n, m, nr, nc, r, c, k, depth + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}