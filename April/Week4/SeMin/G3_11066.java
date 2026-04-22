import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] arr;
        int[] sum;
        for (int tc = 0; tc < t; tc++) {
            int k = Integer.parseInt(br.readLine());
            arr = new int[k];
            sum = new int[k + 1];
            st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i + 1] = sum[i] + arr[i]; 
            }

            int[][] dp = new int[k][k];
            
            for (int len = 1; len < k; len++) {
                for (int i = 0; i + len < k; i++) {
                    int j = i + len;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int p = i; p < j; p++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j] + sum[j + 1] - sum[i]);
                    }
                }
            }
            System.out.println(dp[0][k - 1]);
        }
    }
    
}
