import java.util.*;
import java.io.*;

class Main {
    static int M, N, H;
    static int[][][] boxs;
    
    static int[] dh = {1, -1};
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        boxs = new int[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int i = 0; i< N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int t = Integer.parseInt(st.nextToken());
                    if(t == 1) q.offer(new int[]{h, i , j});
                    boxs[h][i][j] = t;
                }
            }
        }
        System.out.println(bfs());
    }
    
    static int bfs(){
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nh = now[0];
            int ny = now[1];
            int nx = now[2];
            
            for(int i = 0; i < 2; i++){
                int nextH = nh + dh[i];
                if(nextH < 0 || nextH >= H) continue;
                if(boxs[nextH][ny][nx] == 0){
                    boxs[nextH][ny][nx] = boxs[nh][ny][nx] + 1;
                    q.offer(new int[]{ nextH, ny, nx });
                }

            }
                
            for (int j = 0; j < 4; j++ ) {
                int nextY = ny + dy[j];
                int nextX = nx + dx[j];
                if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
                if(boxs[nh][nextY][nextX] == 0){
                    boxs[nh][nextY][nextX] = boxs[nh][ny][nx] + 1;
                    q.offer(new int[]{ nh, nextY, nextX });
                }
            }
        }
        int answer = -3;
        
        for (int h = 0; h < H; h++) {
            for (int i = 0; i< N; i++) {
                for (int j = 0; j < M; j++) {
                    if(boxs[h][i][j] == 0) return -1;
                    answer = Math.max(answer, boxs[h][i][j]);
                }
            }
        }

        if(answer == 1) return 0;
        return answer - 1;
    }


}