import java.util.*;

class Solution {
	String[] answer;
	public String[] solution(String[][] tickets) {
		Arrays.sort(tickets, (a, b) -> b[1].compareTo(a[1]));

		dfs(tickets, "ICN", new boolean[tickets.length], new StringBuilder("ICN"), 0);

		return answer;
	}

	void dfs(String[][] tickets, String cur, boolean[] visited, StringBuilder path, int depth) {
		if(depth == tickets.length) {
			answer = path.toString().split(" ");
			return;
		}

		for(int i = 0; i < tickets.length; i++) {
			if(visited[i] || !cur.equals(tickets[i][0])) continue;

			int length = path.length();
			visited[i] = true;

			path.append(" ").append(tickets[i][1]);
			dfs(tickets, tickets[i][1], visited, path, depth + 1);

			visited[i] = false;
			path.setLength(length);
		}
	}
}