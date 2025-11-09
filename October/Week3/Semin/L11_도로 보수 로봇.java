import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] x;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        K = Integer.parseInt(NM[1]);
        x = new int[N];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(x);
        System.out.println(parametricSearch(1, x[N - 1] - x[0] + 1));
    }

    public static boolean isPossible(int l) {
        int cnt = 1, lastX = x[0];
        for (int i = 1; i < N; i++) {
            if (x[i] - lastX + 1 <= l) continue;
            cnt++;
            lastX = x[i];
        }
        return cnt <= K;
    }

    public static int parametricSearch(int s, int e) {
        if (s > e) return x[N - 1] - x[0] + 1;
        int m = (s + e) / 2;
        if (isPossible(m)) {
            return Math.min(m, parametricSearch(s, m - 1));
        } else {
            return parametricSearch(m + 1, e);
        }
    }
}
