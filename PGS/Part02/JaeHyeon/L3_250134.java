import java.util.*;
class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    int n, m;
    int[][] maze;
    Point rS, rG, bS, bG;
    int[][][][] best;
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] maze) {
        
        this.maze = maze;
        n = maze.length;
        m = maze[0].length;
        
        // 좌표 정리
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int v = maze[i][j];
                if (v == 1)
                    rS = new Point(i, j);
                else if (v == 2)
                    bS = new Point(i, j);
                else if (v == 3)
                    rG = new Point(i, j);
                else if (v == 4)
                    bG = new Point(i, j);
            }
        }
        
        boolean[][] visitedR = new boolean[n][m];
        boolean[][] visitedB = new boolean[n][m];
        visitedR[rS.r][rS.c] = true;
        visitedB[bS.r][bS.c] = true;
        
        dfs(rS, false, visitedR, bS, false, visitedB, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    void dfs(Point red, boolean redEnd, boolean[][] visitedR,
             Point blue, boolean blueEnd, boolean[][] visitedB,
             int step) {
        
        if (step >= answer) return;
        
        if (redEnd && blueEnd) {
            answer = Math.min(answer, step);
            return;
        }
        
        // 빨강 수레 이동
        List<Point> candR = new ArrayList<>();
        if (redEnd) {
            // 도착 시 현재 자리 유지
            candR.add(red);
        } else {
            for (int d = 0; d < 4; d++) {
                int nr = red.r + dr[d];
                int nc = red.c + dc[d];
                if (!(nr >= 0 && nr < n && nc >= 0 && nc < m)) continue;
                if (maze[nr][nc] == 5)  continue;
                if (visitedR[nr][nc])    continue;
                candR.add(new Point(nr, nc));
            }
            
            // 도착 불가능 -> 종료
            if (candR.isEmpty())
                return;
        }
        
        // 파란 수레 이동
        List<Point> candB = new ArrayList<>();
        if (blueEnd) {
            // 도착 시 현재 자리 유지
            candB.add(blue);
        } else {
            for (int d = 0; d < 4; d++) {
                int nr = blue.r + dr[d];
                int nc = blue.c + dc[d];
                if (!(nr >= 0 && nr < n && nc >= 0 && nc < m)) continue;
                if (maze[nr][nc] == 5)  continue;
                if (visitedB[nr][nc])    continue;
                candB.add(new Point(nr, nc));
            }
            
            // 도착 불가능 -> 종료
            if (candB.isEmpty()) return;
        }
        
        // 수레 동시 이동
        for (Point R : candR) {
            for (Point B : candB) {
                // 같은 칸 이동 금지
                if (R.r == B.r && R.c == B.c)   continue;
                // 교차 이동 금지
                boolean cross = (R.r == blue.r && R.c == blue.c) && 
                                (B.r == red.r && B.c == red.c);
                if (cross)  continue;
                
                boolean nRedEnd = redEnd || (R.r == rG.r && R.c == rG.c);
                boolean nBlueEnd = blueEnd || (B.r == bG.r && B.c == bG.c);
                
                boolean rMarked = false, bMarked = false;
                if (!redEnd) {
                    visitedR[R.r][R.c] = true;
                    rMarked = true;
                }
                if (!blueEnd) {
                    visitedB[B.r][B.c] = true;
                    bMarked = true;
                }
                
                dfs(R, nRedEnd, visitedR,
                    B, nBlueEnd, visitedB, step + 1);
                
                if (rMarked)    visitedR[R.r][R.c] = false;
                if (bMarked)    visitedB[B.r][B.c] = false;
            }
        }

    }
}

class Point {
    int r, c;
    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }
}