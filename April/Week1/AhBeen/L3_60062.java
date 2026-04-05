import java.util.*;

class Solution {
	int answer;
	int length;
	int[] extended;
	int[] dist;
	boolean[] visited;

	public int solution(int n, int[] weak, int[] dist) {
		this.length = weak.length;
		this.dist = dist;
		this.answer = dist.length + 1;
		this.visited = new boolean[dist.length];

		extended = new int[length * 2];
		for (int i = 0; i < length; i++) {
			extended[i] = weak[i];
			extended[i + length] = weak[i] + n;
		}

		for (int start = 0; start < length; start++) {
			Arrays.fill(visited, false);
			dfs(start, start, 0);
		}

		return answer == dist.length + 1 ? -1 : answer;
	}

	void dfs(int start, int idx, int used) {
		if (used >= answer) {
			return;
		}

		if (idx >= start + length) {
			answer = Math.min(answer, used);
			return;
		}

		for (int i = 0; i < dist.length; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;

			int coverLimit = extended[idx] + dist[i];
			int nextIdx = idx;

			while (nextIdx < start + length && extended[nextIdx] <= coverLimit) {
				nextIdx++;
			}

			dfs(start, nextIdx, used + 1);

			visited[i] = false;
		}
	}
}