import java.util.*;

class Solution {

	static int h, n, m, k;
	static String[] grid;
	static int[][] panels;
	static int[][] dist;
	static int[] pre;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static class Node {
		int f, r, c;
		Node(int f, int r, int c) {
			this.f = f;
			this.r = r;
			this.c = c;
		}
	}

	public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
		this.h = h;
		this.grid = grid;
		this.n = grid.length;
		this.m = grid[0].length();
		this.panels = panels;
		this.k = panels.length;

		dist = new int[k + 1][k];
		pre = new int[k];

		for (int[] s : seqs) {
			int a = s[0] - 1;
			int b = s[1] - 1;
			pre[b] |= (1 << a);
		}

		int sf = panels[0][0] - 1;
		int sr = panels[0][1] - 1;
		int sc = panels[0][2] - 1;

		dist[k] = bfs(sf, sr, sc);

		for (int i = 0; i < k; i++) {
			int f = panels[i][0] - 1;
			int r = panels[i][1] - 1;
			int c = panels[i][2] - 1;
			dist[i] = bfs(f, r, c);
		}

		int INF = Integer.MAX_VALUE / 2;
		int[][] dp = new int[1 << k][k];
		for (int[] d : dp) Arrays.fill(d, INF);

		for (int i = 0; i < k; i++) {
			if (pre[i] == 0) {
				dp[1 << i][i] = dist[k][i];
			}
		}

		for (int mask = 0; mask < (1 << k); mask++) {
			for (int cur = 0; cur < k; cur++) {
				if (dp[mask][cur] == INF) continue;

				for (int next = 0; next < k; next++) {
					if ((mask & (1 << next)) != 0) continue;

					if ((mask & pre[next]) != pre[next]) continue;

					int nextMask = mask | (1 << next);

					dp[nextMask][next] = Math.min(
						dp[nextMask][next],
						dp[mask][cur] + dist[cur][next]
					);
				}
			}
		}

		int res = INF;
		int full = (1 << k) - 1;
		for (int i = 0; i < k; i++) {
			res = Math.min(res, dp[full][i]);
		}

		return res;
	}

	int[] bfs(int sf, int sr, int sc) {
		int INF = Integer.MAX_VALUE / 2;
		int[][][] visited = new int[h][n][m];
		for (int[][] layer : visited)
			for (int[] row : layer)
				Arrays.fill(row, INF);

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sf, sr, sc));
		visited[sf][sr][sc] = 0;

		int[] result = new int[k];
		Arrays.fill(result, INF);

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int d = visited[cur.f][cur.r][cur.c];

			for (int i = 0; i < k; i++) {
				if (panels[i][0] - 1 == cur.f &&
					panels[i][1] - 1 == cur.r &&
					panels[i][2] - 1 == cur.c) {
					result[i] = d;
				}
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];

				if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
				if (grid[nr].charAt(nc) == '#') continue;

				if (visited[cur.f][nr][nc] > d + 1) {
					visited[cur.f][nr][nc] = d + 1;
					q.add(new Node(cur.f, nr, nc));
				}
			}

			if (grid[cur.r].charAt(cur.c) == '@') {
				for (int nf = 0; nf < h; nf++) {
					if (nf == cur.f) continue;

					int cost = d + Math.abs(nf - cur.f);

					if (visited[nf][cur.r][cur.c] > cost) {
						visited[nf][cur.r][cur.c] = cost;
						q.add(new Node(nf, cur.r, cur.c));
					}
				}
			}
		}

		return result;
	}
}