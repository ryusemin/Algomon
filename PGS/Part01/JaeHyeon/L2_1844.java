import java.util.*;
class Solution {
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<Point> q = new ArrayDeque<>();
        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1);
        }
        
        if (maps[0][0] == 0)    return -1;
        
        q.add(new Point(0, 0));
        visited[0][0] = 1;
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && visited[nr][nc] == -1 && maps[nr][nc] == 1) {
                    q.add(new Point(nr, nc));
                    visited[nr][nc] = visited[now.r][now.c] + 1;
                }
            }
        }
        
        return visited[n - 1][m - 1];
    }
}

class Point {
    int r, c;
    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}