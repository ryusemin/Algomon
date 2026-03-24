import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];
        
        StringTokenizer st;
        
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp[1][1] = arr[1][1];
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                int a = dp[i - 1][j - 1]; 
                int b = dp[i - 1][j];
                dp[i][j] = Math.max(a, b) + arr[i][j];
            }
        }
        
        int result = -1;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, dp[n][i]);
        }
        System.out.println(result);
    }
}