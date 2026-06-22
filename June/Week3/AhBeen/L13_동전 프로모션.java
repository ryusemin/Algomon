import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] dp = new int[m + 1];

		Arrays.fill(dp, 10000);

		dp[0] = 0;

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int c = Integer.parseInt(st.nextToken());

			if("A".equals(type)) {
				for(int j = c; j <= m; j++) {
					dp[j] = Math.min(dp[j], dp[j - c] + 1);
				}
			} else if("B".equals(type)) {
				for(int j = m; j >= c; j--) {
					dp[j] = Math.min(dp[j], dp[j - c] + 1);
				}
			}
		}

		if(dp[m] >= 10000) {
			System.out.println(-1);
		} else {
			System.out.println(dp[m]);
		}
	}
}