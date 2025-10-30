import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void dfs(int x, int y, int M, int N, int[][] cabbage, boolean[][] visit) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
                if (!visit[cx][cy] && cabbage[cx][cy] == 1) {
                    dfs(cx, cy, M, N, cabbage, visit);
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());

        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] cabbage = new int[M][N];
            boolean[][] visit = new boolean[M][N];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine().trim(), " ");
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());
                cabbage[p1][p2] = 1;
            }

            int count = 0;
            for (int x = 0; x < M; x++) {
                for (int y = 0; y < N; y++) {
                    if (cabbage[x][y] == 1 && !visit[x][y]) {
                        dfs(x, y, M, N, cabbage, visit);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }
}
