import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_2004 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long count2 = getCount(n, 2) - getCount(m, 2) - getCount(n - m, 2);
        long count5 = getCount(n, 5) - getCount(m, 5) - getCount(n - m, 5);
        System.out.println(Math.min(count2, count5));
    }

    static long getCount(long num, long divNum) {
        long count = 0;
        while (num > 0) {
            num /= divNum;
            count += num;
        }
        return count;
    }
}
