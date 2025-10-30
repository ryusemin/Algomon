import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }


        Queue<Point> q = new ArrayDeque<>();
        int[][][] visited = new int[n][m][2];

        q.add(new Point(0, 0, 0));
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.r == n - 1 && now.c == m - 1) {
                System.out.println(visited[now.r][now.c][now.canBreak]);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                if (map[nr][nc] == 0) {
                    if (visited[nr][nc][now.canBreak] == 0) {
                        visited[nr][nc][now.canBreak] = visited[now.r][now.c][now.canBreak] + 1;
                        q.add(new Point(nr, nc, now.canBreak));
                    }
                } else {
                    if (now.canBreak == 0 && visited[nr][nc][1] == 0) {
                        visited[nr][nc][1] = visited[now.r][now.c][0] + 1;
                        q.add(new Point(nr, nc, 1));
                    }
                }
            }
        }

        System.out.println(-1);
        return;
    }
}

class Point {
    int r, c, canBreak;

    Point(int r, int c, int canBreak) {
        this.r = r;
        this.c = c;
        this.canBreak = canBreak;
    }
}