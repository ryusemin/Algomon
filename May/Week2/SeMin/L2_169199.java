import java.util.*;

class Solution {
    class Check{
        int y, x;
        int count;
        public Check(int y, int x, int count){
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
    
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] visited;
    static String[] board;
    
    public int solution(String[] board) {
        this.board = board;
        visited = new boolean[board.length][board[0].length()];
        int answer = -1;
        Queue<Check> q = new LinkedList<>();
        
        int startY = -1;
        int startX = -1;
        
        boolean flag = false;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length(); j++){
                char c = board[i].charAt(j);
                if(c == 'R') {
                    startY = i;
                    startX = j;
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        q.offer(new Check(startY, startX, 0));
        visited[startY][startX] = true;
        
        while(!q.isEmpty()){
            Check c = q.poll();
            
            if(board[c.y].charAt(c.x) == 'G'){
                answer = c.count;
                break;
            }
            
            for(int i = 0; i < 4; i++){
                int ny = c.y;
                int nx = c.x;
                while(true){
                    if(!checkRange(ny + dy[i], nx + dx[i])) break;
                    ny += dy[i];
                    nx += dx[i];
                }
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    q.offer(new Check(ny, nx, c.count + 1));
                }
            }
            
        }
        return answer;
    }
    
    static boolean checkRange(int y, int x){
        if(y < 0 || x < 0 || y >= board.length || x >= board[0].length() || board[y].charAt(x) == 'D') return false;
        return true;
    }
}