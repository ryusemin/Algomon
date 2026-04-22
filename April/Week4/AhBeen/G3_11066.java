import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int[] arr;
		int[] prefix;
		int[][] dp;

		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());

			arr = new int[K + 1];
			prefix = new int[K + 1];
			dp = new int[K + 1][K + 1];
			st = new StringTokenizer(br.readLine());
			for(int k = 1; k < K + 1; k++) {
				arr[k] = Integer.parseInt(st.nextToken());
				prefix[k] = prefix[k - 1] + arr[k];
			}

			for(int len = 2; len <= K; len++) {
				for(int i = 1; i <= K - len + 1; i++) {
					int j = i + len - 1;
					dp[i][j] = Integer.MAX_VALUE;

					int sum = prefix[j] - prefix[i - 1];

					for(int k = i; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum);
					}
				}
			}
			sb.append(dp[1][K]).append('\n');
		}
		System.out.println(sb);
	}
}