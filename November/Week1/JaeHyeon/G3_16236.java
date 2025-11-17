import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static int n;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        int sharkR = 0;
        int sharkC = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 9) {
                    sharkR = i;
                    sharkC = j;
                    map[i][j] = 0;
                } else {
                    map[i][j] = num;
                }
            }
        }

        int size = 2;
        int eat = 0;
        int time = 0;

        while (true) {
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], -1);
            }

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{sharkR, sharkC});
            dist[sharkR][sharkC] = 0;

            int targetR = -1;
            int targetC = -1;
            int minDist = Integer.MAX_VALUE;

            while (!q.isEmpty()) {
                int[] now = q.poll();
                int r = now[0];
                int c = now[1];

                int d = dist[r][c];
                if (d > minDist) continue;
                if (map[r][c] > 0 && map[r][c] < size) {
                    if (d < minDist) {
                        minDist = d;
                        targetR = r;
                        targetC = c;
                    } else if (d == minDist) {
                        if (r < targetR || (r == targetR && c < targetC)) {
                            targetR = r;
                            targetC = c;
                        }
                    }
                }

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (dist[nr][nc] != -1) continue;
                    if (map[nr][nc] > size) continue;

                    dist[nr][nc] = d + 1;
                    q.add(new int[]{nr, nc});
                }
            }

            if (targetR == -1) {
                break;
            }

            time += minDist;
            eat++;
            map[targetR][targetC] = 0;
            sharkR = targetR;
            sharkC = targetC;

            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        System.out.println(time);

    }
}
