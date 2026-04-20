import java.util.*;
import java.io.*;

class Main {
    static int[] point;
    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        point = new int[n + 1];
        dp = new Integer[n + 1];
        
        for (int i = 1; i <= n; i++) {
            point[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = point[0];
        dp[1] = point[1];

        if(n >= 2) dp[2] = point[1] + point[2];
        
        System.out.println(find(n));
    }

    static int find(int n){
        if(dp[n] == null){
            dp[n] = Math.max(find(n - 2), find(n - 3) + point[n - 1]) + point[n];
        }
        return dp[n];
    }
}