import java.util.*;
class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public int solution(int[][] land) {
        
        int n = land.length;
        int m = land[0].length;
        
        int[][] map = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Map<Integer, Integer> countMap = new HashMap<>();
        int num = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    num++;
                    int count = 1;
                    Queue<Point> q = new ArrayDeque<>();
                    visited[i][j] = true;
                    q.add(new Point(i, j));
                    map[i][j] = num;
                    
                    while (!q.isEmpty()) {
                        Point now = q.poll();
                        
                        for (int d = 0; d < 4; d++) {
                            int nr = now.r + dr[d];
                            int nc = now.c + dc[d];
                            
                            if (nr >= 0 && nc >= 0 && nr < n && nc < m 
                                && !visited[nr][nc] && land[nr][nc] == 1) {
                                visited[nr][nc] = true;
                                q.add(new Point(nr, nc));
                                map[nr][nc] = num;
                                count++;
                            }
                        }
                    }
                    countMap.put(num, count);                    
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < m; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (map[j][i] != 0) {
                    set.add(map[j][i]);
                }
            }
            
            int sum = 0;
            for (int s : set) {
                sum += countMap.get(s);
            }
            
            answer = Math.max(answer, sum);
        }
        
        
        return answer;
    }
}

class Point {
    int r, c;
    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }
}