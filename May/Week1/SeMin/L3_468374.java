import java.util.*;

class Solution {
    int[][] D = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int n, m, c;
    Application[] applications;

    public int[][] solution(int[][] board, int[][] commands) {
        n = board.length;
        m = board[0].length;
        c = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c = Math.max(c, board[i][j]);
            }
        }

        applications = new Application[c];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 || applications[board[i][j] - 1] != null) continue;

                int s = 1;
                while (i + s < n && board[i + s][j] == board[i][j]) s++;

                applications[board[i][j] - 1] = new Application(i, j, s);
            }
        }

        for (int[] command : commands) {
            Queue<int[]> q = new ArrayDeque<>();
            boolean[] v = new boolean[c];

            q.add(new int[]{command[0] - 1, command[1] - 1});
            v[command[0] - 1] = true;

            while (!q.isEmpty()) {
                int[] cmd = q.poll();
                v[cmd[0]] = false;

                applications[cmd[0]].move(D[cmd[1]]);

                for (int i = 0; i < c; i++) {
                    if (cmd[0] == i) continue;

                    if (!v[i] && applications[cmd[0]].intersect(applications[i])) {
                        q.add(new int[]{i, cmd[1]});
                        v[i] = true;
                    }
                }

                if (applications[cmd[0]].out()) {
                    q.add(cmd);
                    v[cmd[0]] = true;
                }
            }
        }

        int[][] ans = new int[n][m];

        for (int a = 0; a < c; a++) {
            Application app = applications[a];

            for (int i = 0; i < app.size; i++) {
                for (int j = 0; j < app.size; j++) {
                    ans[app.r + i][app.c + j] = a + 1; 
                }
            }
        }

        return ans;
    }

    class Application {
        int r, c;
        int size;

        Application(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        boolean intersect(Application a) {

            return (r + size > a.r) && (a.r + a.size > r) && (c + size > a.c) && (a.c + a.size > c);
        }

        boolean out() {
            return r < 0 || c < 0 || r + size > n || c + size > m;
        }

        void move(int[] d) {
            r += d[0];
            c += d[1];

            if (d[0] == 1 && r + size == n + 1) r -= n;
            if (d[1] == 1 && c + size == m + 1) c -= m;
            if (d[0] == -1 && r == -1) r += n;
            if (d[1] == -1 && c == -1) c += m;
        }
    }
}
