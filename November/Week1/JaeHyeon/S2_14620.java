import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static boolean[][] visited;
    static int answer, n;
    static int[] dr = {0, 1, -1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int cnt, int cost) {
        if (cnt == 3) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int sum = 0;
                boolean ok = true;
                int[][] pos = new int[5][2];

                for (int d = 0; d < 5; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (visited[nr][nc]) {
                        ok = false;
                        break;
                    }

                    sum += map[nr][nc];
                    pos[d][0] = nr;
                    pos[d][1] = nc;
                }

                if (!ok) continue;

                for (int k = 0; k < 5; k++) {
                    visited[pos[k][0]][pos[k][1]] = true;
                }

                dfs(cnt + 1, cost + sum);

                for (int k = 0; k < 5; k++) {
                    visited[pos[k][0]][pos[k][1]] = false;
                }
            }
        }
    }
}
