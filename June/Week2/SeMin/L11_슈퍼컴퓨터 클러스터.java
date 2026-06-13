import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long B;
    static int[] computers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        computers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            computers[i] = Integer.parseInt(st.nextToken());
        }

        long left = 0;
        long right = 2_000_000_000L;

        while (left + 1 < right) {
            long mid = (left + right) / 2;

            if (possible(mid)) left = mid;
            else right = mid;
        }
        System.out.println(left);
    }

    // 최대값 찾기
    static boolean possible(long target) {
        long used = 0;

        for (int i = 0; i < N; i++) {
            if (computers[i] >= target) continue; 

            long diff = target - computers[i]; // 목표값과 차이
            long cost = diff * diff;           // 비용

            if (used + cost > B) return false;
            used += cost;
        }

        return true;
    }
}