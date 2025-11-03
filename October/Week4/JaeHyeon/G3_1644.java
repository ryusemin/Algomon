import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static int n;
    static List<Integer> list;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        primeList(n);

        int start = 0;
        int end = 0;
        int sum = 0;
        int count = 0;

        while (true) {
            if (sum > n) {
                sum -= list.get(start++);
            } else {
                if (sum == n) {
                    count++;
                }
                if (end == list.size()) {
                    break;
                }
                sum += list.get(end++);
            }
        }

        System.out.println(count);
    }

    static void primeList(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (isPrime[i]) {
                list.add(i);
            }
        }
    }
}
