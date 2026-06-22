import java.util.*;
import java.io.*;

public class Main {
    static int[] dp = new int[10001];
    static int n, m;
    static char[] type = new char[1001];
    static int[] coin = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            char t = st.nextToken().charAt(0);
            int c = Integer.parseInt(st.nextToken());
            type[i] = t;
            coin[i] = c;
        }
        for(int i = 1 ; i <= n; i++){
            if(type[i] == 'A'){
                for(int j = 1; j <= m; j++){
                    if(j >= coin[i]){
                        if(dp[j - coin[i]] == Integer.MAX_VALUE) continue;
                        dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
                    }
                }
            }
            else {
                for(int j = m; j >= 1; j--){
                    if(j >= coin[i]){
                        if(dp[j - coin[i]] == Integer.MAX_VALUE) continue;
                        dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
                    }
                }
            }
        }
        int min = (dp[m] == Integer.MAX_VALUE)? -1 : dp[m];
        System.out.println(min); 
    }
}
