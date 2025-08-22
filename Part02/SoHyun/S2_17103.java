import java.io.*;

public class S2_17103 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        boolean[] prime = new boolean[1000001];
        prime[0] = prime[1] = true;

        for (int i = 2; i <= Math.sqrt(1000000); i++) {
            if (prime[i]) {
                continue;
            }

            for (int j = i*i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }

        for (int i = 0; i < t; i++) {
            int count = 0;
            int n = Integer.parseInt(br.readLine());

            for (int j = 1; j <= n/2; j++) {
                if (!prime[j] && !prime[n-j]) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}

