import java.io.*;
import java.util.StringTokenizer;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int DIR_NUM = 8;
    public static final int MAX_N = 15;

    public static int n, m;

    public static int[][] height = new int[MAX_N][MAX_N];

    public static boolean[][] fertilizer = new boolean[MAX_N][MAX_N];
    public static boolean[][] nextFert = new boolean[MAX_N][MAX_N];

    public static int[] dx = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dy = new int[]{1, 1, 0, -1, -1, -1, 0, 1};

    public static void initFertilizer() {
        for(int i = n - 2; i < n; i++)
            for(int j = 0; j < 2; j++)
                fertilizer[i][j] = true;
    }

    public static Pair nextPos(int x, int y, int d, int p) {
        int nx = (x + dx[d] * p + n * p) % n;
        int ny = (y + dy[d] * p + n * p) % n;

        return new Pair(nx, ny);
    }

    public static void move(int d, int p) {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextFert[i][j] = false;

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(fertilizer[i][j]) {
                    Pair nPos = nextPos(i, j, d, p);
                    int nx = nPos.x, ny = nPos.y;
                    nextFert[nx][ny] = true;
                }

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                fertilizer[i][j] = nextFert[i][j];
    }

    public static void grow() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(fertilizer[i][j])
                    height[i][j]++;
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static int getDiagCnt(int x, int y) {
        int cnt = 0;
        for(int i = 1; i < DIR_NUM; i += 2) {
            int nx = x + dx[i], ny = y + dy[i];
            if(inRange(nx, ny) && height[nx][ny] >= 1)
                cnt++;
        }

        return cnt;
    }

    public static void diagonalGrow() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(fertilizer[i][j]) {
                    int cnt = getDiagCnt(i, j);
                    height[i][j] += cnt;
                }
    }

    public static void determineFert() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                if(fertilizer[i][j]) fertilizer[i][j] = false;

                else if(height[i][j] >= 2) {
                    fertilizer[i][j] = true;
                    height[i][j] -= 2;
                }
            }
    }

    public static void simulate(int d, int p) {
        move(d, p);
        grow();
        diagonalGrow();
        determineFert();
    }

    public static int getScore() {
        int sum = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                sum += height[i][j];

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initFertilizer();

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            simulate(d - 1, p);
        }

        System.out.println(getScore());
    }
}