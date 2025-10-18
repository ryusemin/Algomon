class Solution {
	int solution(int[][] land) {
		int answer = 0;
		int[] dp = new int[4];

		for (int j = 0; j < 4; j++) {
			dp[j] = land[0][j];
		}

		for (int i = 1; i < land.length; i++) {
			int[] temp = new int[4];
			for (int j = 0; j < 4; j++) {
				int max = 0;
				for (int k = 0; k < 4; k++) {
					if (k == j) continue;
					max = Math.max(max, dp[k]);
				}

				temp[j] = land[i][j] + max;
			}

			dp = temp;
		}
		for (int d : dp) {
			answer = Math.max(answer, d);
		}

		return answer;
	}
}
