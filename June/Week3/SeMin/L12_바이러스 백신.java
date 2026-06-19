import java.io.*;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int INT_MAX = Integer.MAX_VALUE;
    static final int MAX_N = 50;
    static final int DIR_NUM = 4;

    static int n, m;

    static int[][] a = new int[MAX_N][MAX_N];

    static ArrayList<Pair> hospitals = new ArrayList<>();
    static ArrayList<Pair> selectedHos = new ArrayList<>();

    static Queue<Pair> q = new LinkedList<>();
    static boolean[][] visited = new boolean[MAX_N][MAX_N];
    static int[][] step = new int[MAX_N][MAX_N];

    static int ans = INT_MAX;

    static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static boolean canGo(int x, int y) {
        return inRange(x, y) && a[x][y] != 1 && !visited[x][y];
    }

    static void push(int x, int y, int newStep) {
        q.offer(new Pair(x, y));
        visited[x][y] = true;
        step[x][y] = newStep;
    }

    static void initialize() {
        q.clear();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
                step[i][j] = 0;
            }
        }
    }

    static int findMaxDist() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int dir = 0; dir < DIR_NUM; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (canGo(nx, ny)) {
                    push(nx, ny, step[cur.x][cur.y] + 1);
                }
            }
        }

        int maxDist = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    if (visited[i][j]) {
                        maxDist = Math.max(maxDist, step[i][j]);
                    } else {
                        return INT_MAX;
                    }
                }
            }
        }

        return maxDist;
    }

    static int elapsedTimeToKillAllVirus() {
        initialize();

        for (Pair hospital : selectedHos) {
            push(hospital.x, hospital.y, 0);
        }

        return findMaxDist();
    }

    static void findMinTime(int currIdx, int cnt) {
        if (cnt == m) {
            ans = Math.min(ans, elapsedTimeToKillAllVirus());
            return;
        }

        if (currIdx == hospitals.size()) return;

        findMinTime(currIdx + 1, cnt);

        selectedHos.add(hospitals.get(currIdx));
        findMinTime(currIdx + 1, cnt + 1);
        selectedHos.remove(selectedHos.size() - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());

                if (a[i][j] == 2) {
                    hospitals.add(new Pair(i, j));
                }
            }
        }

        findMinTime(0, 0);

        if (ans == INT_MAX) ans = -1;

        System.out.println(ans);
    }
}