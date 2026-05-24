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
    public static final int DIR_NUM = 4;
    public static final int MAX_M = 8;
    public static final int MAX_N = 8;

    public static int[][] grid = new int[MAX_N][MAX_M];
    public static int n, m;

    public static ArrayList<Pair> emptyPlaces = new ArrayList<>();
    public static ArrayList<Integer> selectedIndices = new ArrayList<>();

    public static Queue<Pair> bfsQ = new LinkedList<>();
    public static boolean[][] visited = new boolean[MAX_N][MAX_M];

    public static int maxEmptyCnt;

    public static boolean canGo(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m && !visited[x][y] && grid[x][y] != 1;
    }

    public static void initializeVisited() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                visited[i][j] = false;
    }

    public static void enqueueFires() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 2) {
                    bfsQ.add(new Pair(i, j));
                    visited[i][j] = true;
                }
    }

    public static void placeFirewalls() {
        for (int i = 0; i < selectedIndices.size(); i++) {
            int idx = selectedIndices.get(i);
            int x = emptyPlaces.get(idx).x;
            int y = emptyPlaces.get(idx).y;
            grid[x][y] = 1;
        }
    }

    public static void removeFirewalls() {
        for (int i = 0; i < selectedIndices.size(); i++) {
            int idx = selectedIndices.get(i);
            int x = emptyPlaces.get(idx).x;
            int y = emptyPlaces.get(idx).y;
            grid[x][y] = 0;
        }
    }

    public static void getArea() {

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        initializeVisited();
        placeFirewalls();
        enqueueFires();

        bfsQ.clear();

        while (!bfsQ.isEmpty()) {
            Pair cur = bfsQ.poll();

            for (int i = 0; i < DIR_NUM; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (canGo(nx, ny)) {
                    bfsQ.add(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        int emptyCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 0)
                    emptyCnt++;
            }
        }

        maxEmptyCnt = Math.max(maxEmptyCnt, emptyCnt);

        removeFirewalls();
    }

    public static void searchCombinations(int currIdx, int cnt) {
        if (cnt == 3) {
            getArea();
            return;
        }

        if (currIdx == emptyPlaces.size())
            return;

        selectedIndices.add(currIdx);
        searchCombinations(currIdx + 1, cnt + 1);
        selectedIndices.remove(selectedIndices.size() - 1);

        searchCombinations(currIdx + 1, cnt);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());

                if (grid[i][j] == 0)
                    emptyPlaces.add(new Pair(i, j));
            }
        }

        searchCombinations(0, 0);

        System.out.print(maxEmptyCnt);
    }
}