import java.util.*;
import java.io.*;

public class S2_2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long cnt2 = count2(n) - count2(m) - count2(n-m);
        long cnt5 = count5(n) - count5(m) - count5(n-m);

        System.out.println(Math.min(cnt2, cnt5));
    }

    public static long count2(long num) {
        long cnt = 0;

        while (num >= 2) {
            cnt += (num / 2);
            num /= 2;
        }
        return cnt;
    }

    public static long count5(long num) {
        long cnt = 0;

        while (num >= 5) {
            cnt += (num / 5);
            num /= 5;
        }
        return cnt;
    }
}
