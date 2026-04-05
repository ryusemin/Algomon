class Solution {
	int[] dx = {1, 0, 0, -1};
	int[] dy = {0, -1, 1, 0};
	char[] op = {'d', 'l', 'r', 'u'};
	String answer = "";
	public String solution(int n, int m, int x, int y, int r, int c, int k) {

		dfs(n, m, x - 1, y - 1, r - 1, c - 1, k, 0, new StringBuilder());

		return answer.equals("") ? "impossible" : answer;
	}

	void dfs(int n, int m, int x, int y, int r, int c, int k, int depth, StringBuilder sb) {
		if(!answer.equals("")) return;

		int remain = k - depth;
		int dist = Math.abs(x - r) + Math.abs(y - c);

		if (dist > remain || (remain - dist) % 2 != 0) return;

		if(depth == k) {
			if(x == r && y == c) {
				answer = sb.toString();
			}
			return;
		}
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			sb.append(op[i]);
			dfs(n, m, nx, ny, r, c, k, depth + 1, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
		return;
	}
}