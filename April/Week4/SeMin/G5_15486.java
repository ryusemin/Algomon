import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n + 2][2];
        int[] dp = new int[n + 2];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
        
            arr[i][0] = t;
            arr[i][1] = p;
        }

        int max = -1;
        for (int i = 1; i <= n + 1; i++) {
            if(max < dp[i]) max = dp[i];

            int nxt = arr[i][0] + i;
            if(nxt <= n + 1) dp[nxt] = Math.max(dp[nxt], max + arr[i][1]);
        }
        System.out.println(dp[n + 1]);

        
        
    }
}