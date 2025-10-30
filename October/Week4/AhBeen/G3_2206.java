import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int x;
        int y;
        int dist;
        int destroy;

        public Node(int x, int y, int dist, int destroy) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.destroy = destroy;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs(map, n, m));
    }

    static int bfs(int[][] map, int n, int m) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        boolean[][][] visited = new boolean[n][m][2];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (map[nx][ny] == 0 && !visited[nx][ny][cur.destroy]) {
                    visited[nx][ny][cur.destroy] = true;
                    q.add(new Node(nx, ny, cur.dist + 1, cur.destroy));
                } else if (map[nx][ny] == 1 && cur.destroy == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    q.add(new Node(nx, ny, cur.dist + 1, 1));
                }
            }
        }
        return -1;
    }
}
