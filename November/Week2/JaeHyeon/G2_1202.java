import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] jewelries = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewelries[i][0] = m;
            jewelries[i][1] = v;
        }

        Arrays.sort(jewelries, (j1, j2) -> {
            return j1[0] - j2[0];
        });

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            int c = Integer.parseInt(br.readLine());

            bags[i] = c;
        }

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);

        long answer = 0;
        int idx = 0;

        for (int i = 0; i < k; i++) {
            int c = bags[i];

            while (idx < n && jewelries[idx][0] <= c) {
                pq.add(jewelries[idx][1]);
                idx++;
            }

            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }
}
