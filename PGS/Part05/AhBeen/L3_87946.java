class Solution {
	public int solution(int k, int[][] dungeons) {
		boolean[] visited = new boolean[dungeons.length];

		return dfs(0, visited, k, dungeons);
	}

	int dfs(int depth, boolean[] visited, int k, int[][] dungeons) {
		int answer = depth;
		for(int i = 0; i < dungeons.length; i++) {
			if(!visited[i] && dungeons[i][0] <= k) {
				visited[i] = true;
				answer = Math.max(answer, dfs(depth + 1 ,visited, k - dungeons[i][1], dungeons));
				visited[i] = false;
			}
		}
		return answer;
	}
}