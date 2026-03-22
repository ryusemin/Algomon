import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        System.out.print(m - gcd(n, m));
    }

    static int gcd(int n, int m){
        while (m > 0){
            int temp = n;
            n = m;
            m = temp % m;
        }

        return n;
    }
}