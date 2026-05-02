class Solution {
	public int solution(int[][] signals) {
		int answer = 0;
		int lcm = 1;

		for(int i = 0; i < signals.length; i++) {
			int cycle = signals[i][0] + signals[i][1] + signals[i][2];
			lcm = getLcm(lcm, cycle);
		}

		for(int t = 0; t <= lcm; t++) {
			boolean isYellow = true;
			for(int i = 0; i < signals.length; i++) {
				int g = signals[i][0];
				int y = signals[i][1];
				int r = signals[i][2];
				int c = g + y + r;

				int remain = (t - 1) % c;
				if(!(g <= remain && remain < g + y)) {
					isYellow = false;
					break;
				}
			}
			if(isYellow) {
				return t;
			}
		}
		return -1;
	}

	int getLcm(int a, int b) {
		return a * b / getGcd(a, b);
	}

	int getGcd(int a, int b) {
		if(b == 0) return a;
		return getGcd(b, a % b);
	}
}