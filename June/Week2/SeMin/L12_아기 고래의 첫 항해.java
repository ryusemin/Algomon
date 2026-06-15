import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] grid;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static int[][] bfs(int sr, int sc) {
        int[][] dist = new int[N][N];

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        dist[sr][sc] = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (inRange(nr, nc) && grid[nr][nc] == 0 && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    static int[] getNext(int r, int c, int d) {
        int[] deltas = {0, 1, -1, 2};

        for (int delta : deltas) {
            int nd = (d + delta + 4) % 4;

            int nr = r + dr[nd];
            int nc = c + dc[nd];

            if (inRange(nr, nc) && grid[nr][nc] == 0 && !visited[nr][nc]) {
                return new int[]{nr, nc, nd};
            }
        }

        return new int[]{-1, -1, -1};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int d = Integer.parseInt(st.nextToken()) - 1;

        int[] dirMap = {0, 2, 1, 3};
        d = dirMap[d];

        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];

        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    total++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        visited[r][c] = true;
        sb.append(r + 1).append(" ").append(c + 1).append("\n");

        int cnt = 1;

        while (cnt < total) {
            while (true) {
                int[] next = getNext(r, c, d);

                if (next[0] == -1) {
                    break;
                }

                r = next[0];
                c = next[1];
                d = next[2];

                visited[r][c] = true;
                cnt++;

                sb.append(r + 1).append(" ").append(c + 1).append("\n");
            }

            if (cnt >= total) {
                break;
            }

            int[][] distFrom = bfs(r, c);

            int tr = -1;
            int tc = -1;
            int minDist = -1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] != 0 || visited[i][j] || distFrom[i][j] == -1) {
                        continue;
                    }

                    if (minDist == -1 || distFrom[i][j] < minDist) {
                        tr = i;
                        tc = j;
                        minDist = distFrom[i][j];
                    }
                }
            }

            int[][] distTo = bfs(tr, tc);

            int[] priority = {1, 2, 3, 0};

            while (r != tr || c != tc) {
                for (int dir : priority) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if (inRange(nr, nc)
                            && grid[nr][nc] == 0
                            && distTo[nr][nc] == distTo[r][c] - 1) {

                        r = nr;
                        c = nc;
                        d = dir;
                        break;
                    }
                }
            }

            visited[r][c] = true;
            cnt++;

            sb.append(r + 1).append(" ").append(c + 1).append("\n");
        }

        System.out.print(sb);
    }
}