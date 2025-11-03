import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        
        int[][] map = new int[n+1][m+1];
        boolean[][][] visited = new boolean[n+1][m+1][2];
        
        for (int i = 1; i <= n; i++) {
            String[] arr = br.readLine().split("");
            for(int j = 1; j <= m; j++){
                map[i][j] = Integer.parseInt(arr[j -1]);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1, 1, 0});
        visited[1][1][0] = true;
        
        while (!q.isEmpty()) {
            int[] yx = q.poll();
            int y = yx[0];
            int x = yx[1];
            int cnt = yx[2];
            int jump = yx[3];

            if(y == n && x == m){
                System.out.println(cnt);
                return;
            }
            int count = cnt + 1;
            
            for (int c = 0; c < 4; c++) {
                int ny = y + dy[c];
                int nx = x + dx[c];
                if(ny >= 1 && nx >=1 && ny <= n && nx <= m ){
                    if(map[ny][nx] == 0){
                        if(jump == 0 && !visited[ny][nx][0]){
                            q.offer(new int[]{ny, nx, count, 0});
                            visited[ny][nx][0] = true;
                        }else if(jump == 1 && !visited[ny][nx][1]){
                            q.offer(new int[]{ny, nx, count, 1});
                            visited[ny][nx][1] = true;
                        }
                    }else if(map[ny][nx] == 1){
                        if(jump == 0){
                            q.offer(new int[]{ny, nx, count, 1});
                            visited[ny][nx][1] = true;
                        }
                    }
                }
        }
    }
        System.out.println(-1);
    }
}