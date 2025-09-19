import java.util.*;

class Solution {
    static int[] di = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int n, m;
    static int[][] map;
    static int[] oil;

    static boolean[][] v;
    static int answer = 0;

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;

        map = land;
        v = new boolean[n][m];
        oil = new int[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[j][i] == 1 && !v[j][i]) {
                    bfs(j, i);
                }
            }
        }

        for (int o : oil) {
            answer = Math.max(answer, o);
        }
        return answer;
    }

    private static void bfs(int x, int y) {
        Set<Integer> set = new HashSet<>();

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {x, y});

        v[x][y] = true;

        int tmp = 1;

        while (!q.isEmpty()) {
            int[] c = q.poll();
            set.add(c[1]);

            for (int i = 0; i < 4; i++) {
                int ni = c[0] + di[i];
                int nj = c[1] + dy[i];


                if (ni < 0 || ni >= n || nj < 0 || nj >= m)
                    continue;

                if (map[ni][nj] == 0 || v[ni][nj])
                    continue;

                v[ni][nj] = true;
                q.add(new int[] {ni, nj});
                tmp++;
            }
        }

        for (int s : set) {
            oil[s] += tmp;
        }
    }
}