import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        
        int ml = maps.length;
        int nl = maps[0].length;
        boolean[][] visited = new boolean[ml][nl];
        int answer = -1;
        Queue<int[]> q = new LinkedList<>();
        
        if(maps[0][0] == 1){
            q.offer(new int[]{0, 0, 1});
            visited[0][0] = true;
        }
        else{
            return -1;
        }
        
        while(!q.isEmpty()){
            int[] now = q.poll();    
            int y = now[0];
            int x = now[1];
            int count = now[2];
            
            if(y == ml - 1 && x == nl - 1 ){
                answer = count;
                break;
            }
            
            for(int i =0 ; i< 4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny >= 0 && nx >= 0 && ny < ml && nx< nl && !visited[ny][nx] && maps[ny][nx] == 1){
                    q.offer(new int[]{ny, nx, count + 1});
                    visited[ny][nx]= true;
                }
            }
        }
        
        return answer;
    }
}