import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX_M = 50;
    public static final int MAX_N = 50;

    public static int n, m;

    public static int currX, currY, currDir;

    public static int[][] grid = new int[MAX_N][MAX_M];

    public static boolean[][] visited = new boolean[MAX_N][MAX_M];

    public static boolean canGo(int x, int y) {
        return grid[x][y] == 0 && !visited[x][y];
    }

    public static boolean simulate() {

        visited[currX][currY] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {

            int leftDir = (currDir - 1 + 4) % 4;

            int nextX = currX + dx[leftDir];
            int nextY = currY + dy[leftDir];

            if (canGo(nextX, nextY)) {

                currX = nextX;
                currY = nextY;
                currDir = leftDir;

                return true;
            }

            else {
                currDir = leftDir;
            }
        }

        int backX = currX - dx[currDir];
        int backY = currY - dy[currDir];

        if (grid[backX][backY] == 0) {

            currX = backX;
            currY = backY;

            return true;
        }

        else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        currX = Integer.parseInt(st.nextToken());
        currY = Integer.parseInt(st.nextToken());
        currDir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {

            boolean moved = simulate();

            if (!moved) {
                break;
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (visited[i][j]) {
                    ans++;
                }
            }
        }

        System.out.print(ans);
    }
}