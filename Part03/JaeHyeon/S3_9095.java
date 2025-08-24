import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S3_9095 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
