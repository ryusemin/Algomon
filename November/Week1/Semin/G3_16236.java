import java.util.*;
import java.io.*;

class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        int[] position = null;

        for (int i = 0; i < N ; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N ; j++) {
                int now = Integer.parseInt(line[j]);
                map[i][j] = now;
                if(now == 9) {
                    position = new int[]{i, j};
                    map[i][j] = 0;
                }
            }   
        }


        int size = 2;
        int eat = 0; // 먹은 물고기 수
        int move = 0; // 움직인 총 거리

        while (true) {
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) ->
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            );
            boolean[][] visit = new boolean[N][N];

            q.add(new int[]{position[0], position[1], 0}); // y좌표, x좌표, 이동한 거리
            visit[position[0]][position[1]] = true;

            boolean ck = false;

            while (!q.isEmpty()) {
                position = q.poll();

                if (map[position[0]][position[1]] != 0 && map[position[0]][position[1]] < size) {
                    map[position[0]][position[1]] = 0; 
                    eat++; 
                    move += position[2];
                    ck = true;
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int ny = position[0] + dy[k];
                    int nx = position[1] + dx[k];

                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || visit[ny][nx] || map[ny][nx] > size) continue;

                    q.add(new int[]{ny, nx, position[2] + 1});
                    visit[ny][nx] = true;
                }
            }

            if (!ck)
                break;

            if (size == eat) { 
                size++;
                eat = 0;
            }
        }


        System.out.println(move);

    }


}