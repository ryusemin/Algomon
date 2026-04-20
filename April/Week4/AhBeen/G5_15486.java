import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N + 2][2];
		int[] dp = new int[N + 2];

		StringTokenizer st;
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}


		int max = Integer.MIN_VALUE;

		for(int i = 1; i < N + 2; i++) {
			if(max < dp[i]) {
				max = dp[i];
			}
			int nT= i + arr[i][0];
			if(nT < N + 2) {
				dp[nT] = Math.max(dp[nT], arr[i][1] + max);
			}
		}
		System.out.println(dp[N + 1]);
	}
}