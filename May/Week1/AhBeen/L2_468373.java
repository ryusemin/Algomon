import java.util.*;

class Solution {
	List<Integer>[] g1, g2, g3;
	int answer = 0;
	int N, K;

	public int solution(int n, int infection, int[][] edges, int k) {
		N = n;
		K = k;

		g1 = new ArrayList[n + 1];
		g2 = new ArrayList[n + 1];
		g3 = new ArrayList[n + 1];

		for(int i = 1; i <= n; i++) {
			g1[i] = new ArrayList<>();
			g2[i] = new ArrayList<>();
			g3[i] = new ArrayList<>();
		}

		for(int[] e : edges) {
			int a = e[0];
			int b = e[1];
			int t = e[2];

			if(t == 1) {
				g1[a].add(b); g1[b].add(a);
			} else if(t == 2) {
				g2[a].add(b); g2[b].add(a);
			} else {
				g3[a].add(b); g3[b].add(a);
			}
		}

		boolean[] infected = new boolean[n + 1];
		infected[infection] = true;

		dfs(0, infected);

		return answer;
	}

	void dfs(int depth, boolean[] infected) {
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			if(infected[i]) cnt++;
		}
		answer = Math.max(answer, cnt);

		if(depth == K) return;

		for(int t = 1; t <= 3; t++) {
			boolean[] next = infected.clone();
			spread(next, t);
			dfs(depth + 1, next);
		}
	}

	void spread(boolean[] infected, int t) {
		Queue<Integer> q = new ArrayDeque<>();

		for(int i = 1; i <= N; i++) {
			if(infected[i]) q.add(i);
		}

		List<Integer>[] g = (t == 1) ? g1 : (t == 2 ? g2 : g3);

		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int nx : g[cur]) {
				if(!infected[nx]) {
					infected[nx] = true;
					q.add(nx);
				}
			}
		}
	}
}