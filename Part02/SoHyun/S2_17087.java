import java.io.*;
import java.util.*;

public class S2_17087 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Math.abs(Integer.parseInt(st.nextToken()) - s);
        }

        int d = arr[0];
        for (int i = 1; i < n; i++) {
            d = gcd(d, arr[i]);
        }

        System.out.println(d);
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;

        return gcd(b, a % b);
    }
}

