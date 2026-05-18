import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] map;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		visited = new boolean[n + 1];
		StringTokenizer st;

		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(1, 0);

		System.out.println(min);
	}

	static void dfs(int idx, int count) {
		if(count == n / 2) {
			getDifference();
			return;
		}

		for(int i = idx; i <= n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(i + 1, count + 1);
			visited[i] = false;
		}
	}

	static void getDifference() {
		int morning = 0;
		int night = 0;

		for(int i = 1; i < n; i++) {
			for(int j = i + 1; j <= n; j++) {
				if(visited[i] && visited[j]) {
					morning += map[i][j] + map[j][i];
				} else if(!visited[i] && !visited[j]) {
					night += map[i][j] + map[j][i];
				}
			}
		}

		int diff = Math.abs(morning - night);
		if(diff == 0) {
			System.out.println(0);
			System.exit(0);
		}
		min = Math.min(Math.abs(morning - night), min);
	}
}