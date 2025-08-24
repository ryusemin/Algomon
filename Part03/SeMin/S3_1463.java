import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            int count = dp[i - 1] + 1;
            if (i % 2 == 0) count = Math.min(count, dp[i / 2] + 1);
            if (i % 3 == 0) count = Math.min(count, dp[i / 3] + 1);
            dp[i] = count;
        }

        System.out.println(dp[n]);
    }
}