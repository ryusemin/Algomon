import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, s;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        if (sum < s) {
            System.out.println(0);
            return;
        }

        int end = 0;
        int total = arr[0];
        int len = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            while (end < n && total < s) {
                end++;
                if (end != n) {
                    total += arr[end];
                }
            }

            if (end == n) break;
            len = Math.min(len, end - start + 1);
            total -= arr[start];
        }

        System.out.println(len);
    }
}