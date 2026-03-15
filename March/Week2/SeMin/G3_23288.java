import java.io.*;
import java.util.*;

public class Main {
    public static int[] dice = {1, 2, 3, 4, 5, 6};

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static int[][] map;
    public static int N, M, K, dir;
    public static int d_x, d_y;

    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dir = 0;
        int score = 0;
        int rotate_cnt = 1;
        d_x = 1;
        d_y = 1;
        while (rotate_cnt++ <= K){
            d_x += dx[dir];
            d_y += dy[dir];
            if(d_x < 1 || d_x > N || d_y < 1 || d_y > M){
                d_x -= dx[dir];
                d_y -= dy[dir];
                dir = (dir+2) % 4;
                d_x += dx[dir];
                d_y += dy[dir];
            }

            change_dice();
            int cnt = bfs();
            score += (cnt*map[d_x][d_y]);

            if(dice[5] > map[d_x][d_y]){
                dir = (dir + 1) % 4;
            } else if(dice[5] < map[d_x][d_y]){
                dir = (dir==0? 3 : dir-1);
            }
        }

        sb.append(score);
        System.out.println(sb.toString());
    }

    public static void change_dice(){
        if(dir == 0){
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = temp;

        } else if(dir == 1){
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        } else if(dir == 2){
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
        } else if(dir == 3){
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;
        }
    }

    public static int bfs(){
        boolean[][] visited = new boolean[N+1][M+1];
        int cnt = 1;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(d_x, d_y));
        visited[d_x][d_y] = true;
        while (!q.isEmpty()){
            Node node = q.poll();
            for(int d = 0; d < 4; d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if(1 <= nx && nx <= N && 1 <= ny && ny <= M){
                    if(!visited[nx][ny] && map[nx][ny] == map[d_x][d_y]){
                        visited[nx][ny] = true;
                        cnt++;
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
        return cnt;
    }


}