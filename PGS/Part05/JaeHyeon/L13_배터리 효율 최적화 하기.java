import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static List<List<int[]>> result = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                List<int[]> now = new ArrayList<>();
                boolean[][] visited = new boolean[n][m];

                now.add(new int[]{i, j});
                visited[i][j] = true;

                dfs(now, visited, 1, 5);
            }
        }

        System.out.println(answer);
    }

    static void dfs(List<int[]> now, boolean[][] visited, int depth, int target) {
        if (depth == target) {
            for (List<int[]> l : result) {
                int count = 0;
                int sum = 0;

                for (int[] a : now) {
                    sum += map[a[0]][a[1]];
                }

                for (int[] b : l) {
                    for (int[] a : now) {
                        if (a[0] == b[0] && a[1] == b[1]) {
                            count++;
                        }
                    }
                    sum += map[b[0]][b[1]];
                }

                if (count == 2) {
                    answer = Math.max(answer, sum);
                }
            }

            result.add(new ArrayList<>(now));
            if (result.size() > 1000) {
                result.remove(0);
            }
            return;
        }

        int nowSize = now.size();
        for (int i = 0; i < nowSize; i++) {
            int[] cur = now.get(i);
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    now.add(new int[]{nr, nc});
                    dfs(now, visited, depth + 1, target);
                    now.remove(now.size() - 1);
                    visited[nr][nc] = false;
                }
            }
        }
    }
}
