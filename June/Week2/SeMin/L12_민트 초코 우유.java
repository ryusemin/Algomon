import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static int[][] F;
    static int[][] B;

    static int[][] dxy = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static int getFoodCount(int food) {
        if (food == 1 || food == 2 || food == 4) return 1;
        if (food == 3 || food == 5 || food == 6) return 2;
        return 3;
    }

    static Point getHeadAndAdd(boolean[][] visited, int x, int y) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(x, y));
        visited[x][y] = true;

        int cnt = 1;
        int hx = x;
        int hy = y;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dxy[dir][0];
                int ny = cy + dxy[dir][1];

                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (F[cx][cy] != F[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));

                cnt++;

                if (B[nx][ny] > B[hx][hy]) {
                    hx = nx;
                    hy = ny;
                } else if (B[nx][ny] == B[hx][hy]) {
                    if (nx < hx) {
                        hx = nx;
                        hy = ny;
                    } else if (nx == hx && ny < hy) {
                        hx = nx;
                        hy = ny;
                    }
                }
            }
        }

        B[hx][hy] += cnt;
        return new Point(hx, hy);
    }

    static List<Point> step2() {
        boolean[][] visited = new boolean[N][N];
        List<Point> heads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    heads.add(getHeadAndAdd(visited, i, j));
                }
            }
        }

        return heads;
    }

    static void step3(List<Point> heads) {
        heads.sort((a, b) -> {
            int foodA = getFoodCount(F[a.x][a.y]);
            int foodB = getFoodCount(F[b.x][b.y]);

            if (foodA != foodB)
                return Integer.compare(foodA, foodB);

            if (B[a.x][a.y] != B[b.x][b.y])
                return Integer.compare(B[b.x][b.y], B[a.x][a.y]);

            if (a.x != b.x)
                return Integer.compare(a.x, b.x);

            return Integer.compare(a.y, b.y);
        });

        boolean[][] defend = new boolean[N][N];

        for (Point head : heads) {
            int x = head.x;
            int y = head.y;

            if (defend[x][y]) continue;

            int power = B[x][y] - 1;
            int dir = B[x][y] % 4;

            B[x][y] = 1;

            int food = F[x][y];

            int cx = x;
            int cy = y;

            while (true) {
                cx += dxy[dir][0];
                cy += dxy[dir][1];

                if (!isRange(cx, cy) || power <= 0)
                    break;

                if (food == F[cx][cy])
                    continue;

                defend[cx][cy] = true;

                if (power > B[cx][cy]) {
                    F[cx][cy] = food;
                    power -= (B[cx][cy] + 1);
                    B[cx][cy]++;
                } else {
                    F[cx][cy] |= food;
                    B[cx][cy] += power;
                    break;
                }
            }
        }
    }

    static void printAll(StringBuilder sb) {
        long[] sum = new long[8];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum[F[i][j]] += B[i][j];
            }
        }

        sb.append(sum[7]).append(" ")
          .append(sum[6]).append(" ")
          .append(sum[5]).append(" ")
          .append(sum[3]).append(" ")
          .append(sum[1]).append(" ")
          .append(sum[2]).append(" ")
          .append(sum[4]).append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        F = new int[N][N];
        B = new int[N][N];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();

            for (int j = 0; j < N; j++) {
                char ch = row.charAt(j);

                if (ch == 'T') F[i][j] = 4;
                else if (ch == 'C') F[i][j] = 2;
                else F[i][j] = 1;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int day = 0; day < T; day++) {
            List<Point> heads = step2();

            step3(heads);

            printAll(sb);
        }

        System.out.print(sb);
    }
}