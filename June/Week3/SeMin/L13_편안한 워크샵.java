import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[][] grid;
    static int[][][] dp;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        dp = new int[n][n][k + 1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.min(ans, find(i, j, k));
            }
        }

        if (ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);


    }

    static int find(int i, int j, int l){
        if(dp[i][j][l] != -1) return dp[i][j][l];

        if(l == 1) {
            dp[i][j][l] = 0;
            return 0;
        }

        int best = Integer.MAX_VALUE;

        for(int dir = 0; dir < 4; dir++){
            int nx = i + dx[dir];
            int ny = j + dy[dir];

            if( !(nx < 0 || ny < 0 || nx >= n || ny >= n) && grid[nx][ny] > grid[i][j] ){
                best = Math.min(best, Math.max(find(nx, ny, l - 1), grid[nx][ny] - grid[i][j]));
            } 
        }

        dp[i][j][l] = best;
        return best;
    }
}