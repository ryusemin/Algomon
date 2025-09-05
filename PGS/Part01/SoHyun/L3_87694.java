import java.util.*;

class Solution {
    static int[][] graph, v;
    static int[] dx, dy;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        graph = new int[102][102];
        v = new int[102][102];

        dx = new int[] {1, 0, -1, 0};
        dy = new int[] {0, 1, 0, -1};

        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;

            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        if (graph[y][x] != 2) {
                            graph[y][x] = 1;
                        }
                    }
                    else {
                        graph[y][x] = 2;
                    }
                }
            }
        }

        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    static int bfs(int startX, int startY, int tX, int tY) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startX, startY});
        v[startY][startX] = 1;

        while (!q.isEmpty()) {
            int[] c = q.poll();
            int x = c[0];
            int y = c[1];

            if (x == tX && y == tY) {
                return v[y][x] - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 < nx && nx < 102 && 0 < ny && ny < 102 && v[ny][nx] == 0 && graph[ny][nx] == 1) {
                    q.offer(new int[] {nx, ny});
                    v[ny][nx] = v[y][x] + 1;
                }
            }
        }
        return 0;
    }
}