class Solution {
	int answer = 1;
	public int solution(int dist_limit, int split_limit) {
		dfs(1, 1, 1, 0, dist_limit, split_limit);
		return answer;
	}

	void dfs(long cur, long used, long split, long leaf, int distLimit, int splitLimit) {
		if(used > distLimit) return;

		answer = (int)Math.max(answer, leaf + cur);

		for(int i = 2; i <= 3; i++) {
			long nxSplit = split * i;
			if(nxSplit > splitLimit) continue;

			long nxNodes = cur * i;

			long remain = distLimit - used;

			long nxCur = Math.min(nxNodes, remain);

			long nxLeaf = leaf + (nxNodes - nxCur);

			dfs(nxCur, used + nxCur, nxSplit, nxLeaf, distLimit, splitLimit);
		}
	}
}