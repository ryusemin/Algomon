import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++ ) {
            String[] mnk = br.readLine().split(" ");
            int m = Integer.parseInt(mnk[0]); // 배추밭 가로길이
            int n = Integer.parseInt(mnk[1]); // 배추밭 세로길이
            int k = Integer.parseInt(mnk[2]); // 배추 위치 개수
            
            int[][] map = new int[n][m];
            boolean[][] visited = new boolean[n][m];
            
            for (int i =0; i< k; i++) {
                String[] xy = br.readLine().split(" ");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                map[y][x] = 1;
            }
            Queue<int[]> q = new LinkedList<>();

            int count = 0;
            
            for (int i = 0; i < n; i++) {
                 for (int j = 0; j < m; j++) {
                    if(map[i][j] == 1 && !visited[i][j]){
                        count++;
                        q.offer(new int[]{i, j});
                        visited[i][j] = true;
                        while (!q.isEmpty()) {
                            int[] yx = q.poll();
                            int y = yx[0];
                            int x = yx[1];
                            for (int c = 0; c < 4; c++) {
                                int ny = y + dy[c];
                                int nx = x + dx[c];
                                if(ny >= 0 && nx >=0 && ny <n && nx < m && map[ny][nx] == 1 && !visited[ny][nx]){
                                    q.offer(new int[]{ny, nx});
                                    visited[ny][nx] = true;
                                }
                            }   
                        }
                            
                    }
                        
                }
            }   
            System.out.println(count);
        }
    }
}