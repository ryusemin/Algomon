import java.util.*;

class Solution {
    static int min = Integer.MAX_VALUE;
    
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    
    static boolean[][][] v;
    static boolean finishedRed = false;
    static boolean finishedBlue = false;
    
    public int solution(int[][] maze) {
        int answer = 0;
        v = new boolean[maze.length][maze[0].length][2];
        
        Pos curRed = null;
        Pos curBlue = null;
        
        for(int i=0; i<maze.length; i++) {
            for(int j=0; j<maze[0].length; j++) {
                if(maze[i][j] == 1) curRed = new Pos(i,j);
                if(maze[i][j] == 2) curBlue = new Pos(i,j);
            }
        }
        
        v[curRed.r][curRed.c][0] = true;
        v[curBlue.r][curBlue.c][1] = true;
        bt(maze, 0, curRed, curBlue);
        answer = min == Integer.MAX_VALUE ? 0 : min;
        return answer;
    }
    
    public void bt(int[][] maze, int cnt, Pos curRed, Pos curBlue) {
        if(finishedRed && finishedBlue) {
            min = Math.min(cnt, min);
            return;
        }
        
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                Pos red = finishedRed ? curRed : new Pos(curRed.r + dr[i], curRed.c + dc[i]);
                Pos blue = finishedBlue ? curBlue : new Pos(curBlue.r + dr[j], curBlue.c + dc[j]);
                
                if(isPossible(maze, curRed, curBlue, red, blue)) {                    
                    v[red.r][red.c][0] = true;
                    v[blue.r][blue.c][1] = true;
                    if(maze[red.r][red.c] == 3) finishedRed = true;
                    if(maze[blue.r][blue.c] == 4) finishedBlue = true;
                    
                    bt(maze, cnt+1, red, blue);
                    
                    v[red.r][red.c][0] = false;
                    v[blue.r][blue.c][1] = false;
                    finishedRed = false;
                    finishedBlue = false;
                }
            }
        }
    }
    
    public boolean isPossible(int[][] maze, Pos curRed, Pos curBlue, Pos red, Pos blue) {
        if(red.r < 0 || red.c < 0 || red.r>=maze.length || red.c>=maze[0].length) return false;
        if(blue.r < 0 || blue.c < 0 || blue.r>=maze.length || blue.c>=maze[0].length) return false;

        if(!finishedRed && v[red.r][red.c][0]) return false;
        if(!finishedBlue && v[blue.r][blue.c][1]) return false;
        
        if(maze[red.r][red.c] == 5) return false;
        if(maze[blue.r][blue.c] == 5) return false;
        
        if((red.r == curBlue.r && red.c == curBlue.c) && (blue.r == curRed.r && blue.c == curRed.c)) return false;
        
        if(red.r == blue.r && red.c == blue.c) return false;
        
        return true;
    }
    
    static class Pos{
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}