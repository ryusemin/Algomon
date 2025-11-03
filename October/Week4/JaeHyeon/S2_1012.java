import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int t, m, n, k;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            boolean[][] map = new boolean[n][m];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            }

            int answer = 0;
            boolean[][] visited = new boolean[n][m];
            Queue<Point> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] && !visited[i][j]) {
                        answer++;
                        q.add(new Point(i, j));
                        visited[i][j] = true;

                        while (!q.isEmpty()) {
                            Point now = q.poll();

                            for (int d = 0; d < 4; d++) {
                                int nr = now.r + dr[d];
                                int nc = now.c + dc[d];

                                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                                if (!map[nr][nc] || visited[nr][nc])   continue;

                                q.add(new Point(nr, nc));
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
}

class Point {
    int r, c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}