import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int n, m;
    static int[][] arr;

    static List<Pair> virus = new ArrayList<>();
    static List<Pair> empty = new ArrayList<>();
    static List<Pair> select = new ArrayList<>();

    static boolean[][] visited;

    static int maxCnt = 0;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 0) {
                    empty.add(new Pair(i, j));
                }
                else if(arr[i][j] == 2) {
                    virus.add(new Pair(i, j));
                }
            }
        }

        dfs(0, 0);

        System.out.println(maxCnt);
    }

    static void dfs(int idx, int depth) {
        if(depth == 3) {
            bfs();
            return;
        }

        if(idx == empty.size()) {
            return;
        }

        select.add(empty.get(idx));
        dfs(idx + 1, depth + 1);

        select.remove(select.size() - 1);
        dfs(idx + 1, depth);
    }

    static void bfs() {
        visited = new boolean[n][m];

        for(Pair p : select) {
            arr[p.y][p.x] = 1;
        }

        Queue<Pair> q = new LinkedList<>();

        for(Pair p : virus) {
            q.offer(new Pair(p.y, p.x));
            visited[p.y][p.x] = true;
        }

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            for(int dir = 0; dir < 4; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if(ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }

                if(visited[ny][nx]) {
                    continue;
                }

                if(arr[ny][nx] == 1) {
                    continue;
                }

                visited[ny][nx] = true;
                q.offer(new Pair(ny, nx));
            }
        }

        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == 0 && !visited[i][j]) {
                    cnt++;
                }
            }
        }

        maxCnt = Math.max(maxCnt, cnt);

        for(Pair p : select) {
            arr[p.y][p.x] = 0;
        }
    }
}