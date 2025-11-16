import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<CCTV> list;
    static int answer;
    static int[][][] dirs = {
            {},
            {{0},{1},{2},{3}},
            {{0,1},{2,3}},
            {{0,3},{3,1},{1,2},{2,0}},
            {{0,2,3},{0,1,3},{1,2,3},{0,1,2}},
            {{0,1,2,3}}
    };
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    list.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int idx) {
        if (idx == list.size()) {
            answer = Math.min(answer, countZero());
            return;
        }

        CCTV c = list.get(idx);

        for (int[] dest : dirs[c.type]) {
            List<int[]> changed = new ArrayList<>();
            for (int d : dest) {
                int nr = c.r + dr[d];
                int nc = c.c + dc[d];

                while (0 <= nr && nr < n && 0 <= nc && nc < m) {

                    if (map[nr][nc] == 6) break;

                    if (map[nr][nc] == 0) {
                        map[nr][nc] = -1;
                        changed.add(new int[]{nr, nc});
                    }

                    nr += dr[d];
                    nc += dc[d];
                }
            }

            dfs(idx + 1);

            for (int[] pos : changed) {
                map[pos[0]][pos[1]] = 0;
            }
        }
    }

    static int countZero() {
        int cnt = 0;
        for (int[] row : map) {
            for (int v : row) {
                if (v == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

class CCTV {
    int r, c, type;

    CCTV(int r, int c, int type) {
        this.r = r;
        this.c = c;
        this.type = type;
    }
}
