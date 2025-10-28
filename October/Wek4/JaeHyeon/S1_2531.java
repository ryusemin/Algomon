import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, d, k, c;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());


        int[] dishes = new int[n + k];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            dishes[i] = num;
        }

        int[] cnt = new int[d + 1];
        int count = 0;

        for (int i = 0; i < k; i++) {
            if (cnt[dishes[i]]++ == 0) {
                count++;
            }
        }

        int answer = count + (cnt[c] == 0 ? 1 : 0);

        for (int i = 1; i < n; i++) {
            int out = dishes[i - 1];
            int in = dishes[(i + k - 1) % n];

            if (--cnt[out] == 0) count--;
            if (cnt[in]++ == 0) count++;

            int cur = count + (cnt[c] == 0 ? 1 : 0);
            answer = Math.max(answer, cur);
        }

        System.out.println(answer);
    }
}
