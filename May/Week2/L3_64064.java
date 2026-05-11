import java.util.*;

class Solution {
	Set<Set<String>> result = new HashSet<>();
	boolean[] visited;
	public int solution(String[] user_id, String[] banned_id) {
		visited = new boolean[user_id.length];
		dfs(user_id, banned_id, new HashSet<>(), 0);
		return result.size();
	}

	void dfs(String[] user_id, String[] banned_id, Set<String> cur, int depth) {
		if(depth == banned_id.length) {
			result.add(new HashSet<>(cur));
			return;
		}

		for(int i = 0; i < user_id.length; i++) {
			if(visited[i]) continue;
			if(user_id[i].length() != banned_id[depth].length()) continue;
			if(!isMatch(user_id[i], banned_id[depth])) continue;

			visited[i] = true;
			cur.add(user_id[i]);
			dfs(user_id, banned_id, cur, depth + 1);
			visited[i] = false;
			cur.remove(user_id[i]);
		}
	}

	boolean isMatch(String user, String ban) {
		for(int i = 0; i < user.length(); i++) {
			if(ban.charAt(i) == '*') continue;
			if(user.charAt(i) != ban.charAt(i)) return false;
		}
		return true;
	}
}