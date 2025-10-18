import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, t;
    static long[][] map, forward, backward;
    static long[][][] cycle;
    static long answer = Long.MIN_VALUE / 4;
    static final long NEG = Long.MIN_VALUE / 4;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new long[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Long.parseLong(st.nextToken());
            }
        }

        forward = new long[n + 2][n + 2];
        for (long[] row : forward) 
            Arrays.fill(row, NEG);
        forward[1][1] = map[1][1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;
                long up = forward[i - 1][j];
                long left = forward[i][j - 1];
                long best = Math.max(up, left);
                if (best > NEG / 2) 
                    forward[i][j] = best + map[i][j];
            }
        }

        backward = new long[n + 2][n + 2];
        for (long[] row : backward) 
            Arrays.fill(row, NEG);
        backward[n][n] = map[n][n];
        for (int i = n; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                if (i == n && j == n) continue;
                long down = backward[i + 1][j];
                long right = backward[i][j + 1];
                long best = Math.max(down, right);
                if (best > NEG / 2) 
                    backward[i][j] = best + map[i][j];
            }
        }

        cycle = new long[n + 2][n + 2][t + 1];
        for (long[][] arr2 : cycle)
            for (long[] arr1 : arr2)
                Arrays.fill(arr1, NEG);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cycle[i][j][0] = map[i][j];
            }
        }

        for (int d = 1; d <= t; d++) {
            for (int i = n; i >= 1; i--) {
                for (int j = n; j >= 1; j--) {
                    long best = Math.max(cycle[i + 1][j][d - 1], cycle[i][j + 1][d - 1]);
                    if (best > NEG / 2) 
                        cycle[i][j][d] = best + map[i][j];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                long total = forward[i][j] + backward[i][j] - map[i][j];
                if (cycle[i][j][t] > NEG / 2) 
                    total += cycle[i][j][t];
                answer = Math.max(answer, total);
            }
        }

        System.out.println(answer);
    }
}
