import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k, answer;
    public static void main(String[] args) throws Exception {
        
        answer = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[] dist = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 0;
        int hi = dist[n - 1] -  dist[0];
        while(lo <= hi) {
            int mid = (lo + hi) / 2;

            int position = dist[0];
            int cnt = 1;

            for (int i = 1; i < n; i++) {
                if (dist[i] - position > mid) {
                    position = dist[i];
                    cnt++;
                }
            }

            if (cnt <= k) {
                answer = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(answer + 1);
    }
}