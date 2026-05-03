mport java.util.*;

class Solution {
    int N, M, K;
    char[][] map;
    int[] elv, req;
    int[][] dp;

    int INF = 987654321;
    int[] dy = {0, 0, 1, -1};
    int[] dx = {1, -1, 0, 0};

    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        N = grid.length;
        M = grid[0].length();
        K = panels.length;
        map = new char[N][M];
        elv = new int[2];
        for (int i = 0; i < N; i++) {
            map[i] = grid[i].toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '@') {
                    elv[0] = i;
                    elv[1] = j;
                }
            }
        }
        req = new int[K];
        for (int[] seq : seqs) {
            int a = seq[0] - 1;
            int b = seq[1] - 1;
            req[b] |= (1 << a);
        }

        int[][] dist = new int[K + 1][K + 1];
        int[][] pos = new int[K + 1][2];
        for (int i = 0; i < K; i++) {
            pos[i][0] = panels[i][1] - 1;
            pos[i][1] = panels[i][2] - 1;
        }
        pos[K][0] = elv[0];
        pos[K][1] = elv[1];

        for (int i = 0; i <= K; i++) {
            int[][] m = new int[N][M];
            for (int[] r : m) {
                Arrays.fill(r, -1);
            }
            ArrayDeque<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{pos[i][0], pos[i][1]});
            m[pos[i][0]][pos[i][1]] = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int y = cur[0];
                int x = cur[1];

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M || m[ny][nx] != -1 || map[ny][nx] == '#') continue;

                    m[ny][nx] = m[y][x] + 1;
                    queue.offer(new int[]{ny, nx});
                }
            }

            for (int j = 0; j <= K; j++) {
                dist[i][j] = m[pos[j][0]][pos[j][1]];
            }
        }

        dp = new int[1 << K][K];
        for (int[] d : dp) {
            Arrays.fill(d, INF);
        }
        dp[0][0] = 0;

        for (int mask = 0; mask < (1 << K); mask++) {
            for (int a = 0; a < K; a++) {
                if (dp[mask][a] == INF) continue;

                for (int b = 0; b < K; b++) {
                    if ((mask & (1 << b)) == 0 && (req[b] & mask) == req[b]) {
                        int aFloor = panels[a][0];
                        int bFloor = panels[b][0];

                        int cost = 0;
                        if (aFloor == bFloor) cost = dist[a][b];
                        else cost = dist[a][K] + Math.abs(aFloor - bFloor) + dist[K][b];

                        int nextMask = mask | (1 << b);
                        dp[nextMask][b] = Math.min(dp[nextMask][b], dp[mask][a] + cost);
                    }
                }
            }
        }

        int answer = INF;
        int allMask = (1 << K) - 1;
        for (int i = 0; i < K; i++) {
            answer = Math.min(answer, dp[allMask][i]);
        }
        return answer;
    }
}