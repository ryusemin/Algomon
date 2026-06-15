import java.io.*;
import java.util.*;

public class Main {
    public static final int NOT_ROTATE = 0;
    public static final int ROTATE_CCW = -1;
    public static final int ROTATE_CW = 1;
    public static final int MAX_M = 8;
    public static final int MAX_N = 4;

    public static int n = 4, m = 8, k;
    public static char[][] a = new char[MAX_N + 1][MAX_M + 1];

    public static int[] rotateDir = new int[MAX_N + 1];

    public static void shift(int currNum, int currDir) {
        if (currDir == ROTATE_CW) {
            char temp = a[currNum][m];

            for (int i = m; i >= 2; i--)
                a[currNum][i] = a[currNum][i - 1];

            a[currNum][1] = temp;
        } else {
            char temp = a[currNum][1];

            for (int i = 1; i <= m - 1; i++)
                a[currNum][i] = a[currNum][i + 1];

            a[currNum][m] = temp;
        }
    }

    public static int flip(int currDir) {
        return currDir == ROTATE_CW ? ROTATE_CCW : ROTATE_CW;
    }

    public static void simulate(int startNum, int startDir) {
        for (int i = 1; i <= n; i++)
            rotateDir[i] = NOT_ROTATE;

        rotateDir[startNum] = startDir;

        for (int i = startNum - 1; i >= 1; i--) {
            if (a[i][3] != a[i + 1][7]) rotateDir[i] = flip(rotateDir[i + 1]);
            else break;
        }

        for (int i = startNum + 1; i <= n; i++) {
            if (a[i][7] != a[i - 1][3]) rotateDir[i] = flip(rotateDir[i - 1]);
            else break;
        }

        for (int i = 1; i <= n; i++) {
            if (rotateDir[i] != NOT_ROTATE) shift(i, rotateDir[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i <= n; i++) {
            String row = br.readLine();

            for (int j = 1; j <= m; j++) {
                a[i][j] = row.charAt(j - 1);
            }
        }

        k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int startNum = Integer.parseInt(st.nextToken());
            int startDir = Integer.parseInt(st.nextToken());

            simulate(startNum, startDir);
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            if (a[i][1] == '1') ans += (1 << (i - 1));
        }
        System.out.println(ans);
    }
}