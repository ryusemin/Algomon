import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, c;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            dist[i] = num;
        }

        Arrays.sort(dist);

        int result = 0;
        int left = 0;
        int right = dist[n - 1] - dist[0];

        while (left <= right) {
            int cnt = 1;
            int before = dist[0];

            int mid = (right + left) / 2;

            for (int i = 1; i < n; i++) {
                if (dist[i] - before >= mid) {
                    cnt++;
                    before = dist[i];
                }
            }

            if (cnt >= c) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}