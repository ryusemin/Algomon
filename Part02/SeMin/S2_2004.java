import java.util.*;
import java.io.*;

class S2_2004 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long count2 = getCount(n, 2) - getCount(m, 2) - getCount(n - m, 2);
        long count5 = getCount(n, 5) - getCount(m, 5) - getCount(n - m, 5);
        System.out.println(Math.min(count2, count5));
    }

    static long getCount(int num, int divNum) {
        long count = 0;
        while (num > 0) {
            num /= divNum;
            count += num;
        }
        return count;
    }
}