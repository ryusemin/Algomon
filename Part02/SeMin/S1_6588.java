import java.io.*;
import java.util.*;

class S1_6588 {
    static final int MAX = 1_000_000;
    static boolean[] isPrime = new boolean[MAX + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sieve();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            boolean found = false;
            for (int a = 3; a <= n / 2; a += 2) {
                if (isPrime[a] && isPrime[n - a]) {
                    sb.append(n).append(" = ").append(a).append(" + ").append(n - a).append('\n');
                    found = true;
                    break;
                }
            }
            if (!found) {
                sb.append("Goldbach's conjecture is wrong.").append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    static void sieve() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= MAX; j += i) {
                isPrime[j] = false;
            }
        }
    }
}
