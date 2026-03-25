import java.io.*;
import java.util.*;

public class Main {
    static int N; 
    static int M; 
    static int[][] graph; 
    static boolean[][] visited; 
    static int count = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()); 

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) { 
                    q.add(new int[]{i, j});
                }
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        while (!q.isEmpty()) { 
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (graph[nx][ny] == 0) {
                        q.add(new int[]{nx, ny});
                        graph[nx][ny] = graph[x][y] + 1; 
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0)  return -1;
                count = Math.max(count, graph[i][j]);
            }
        }

        if (count == 1)  return 0; 
        else return count-1;
    }
}   
