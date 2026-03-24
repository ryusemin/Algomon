import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < i + 1; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i + 1; j++) {
				if(j == 0) {
					dp[i][j] += dp[i - 1][j];
				} else if(i == j) {
					dp[i][j] += dp[i - 1][j - 1];
				} else {
					dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
				}
			}
		}

		int max = 0;
		int[] arr = dp[N - 1];
		for(int a : arr) {
			max = Math.max(max, a);
		}
		System.out.println(max);
	}
}