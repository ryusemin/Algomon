import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2_17103 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean[] isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(1000000); i++) {
            if (!isPrime[i]) continue;
            for (int j = 2; i * j <= 1000000; j++) {
                isPrime[i * j] = false;
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int count = 0;
            int N = Integer.parseInt(br.readLine());
            for (int j = 2; j <= N / 2; j++) {
                if (isPrime[j] && isPrime[N - j]) {
                    count++;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb.toString());
    }
}
