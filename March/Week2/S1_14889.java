import java.io.*;
import java.util.*;

public class Main {
	static int answer = Integer.MAX_VALUE;;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[] visited = new boolean[N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, N, map, visited);
		System.out.println(answer);
	}

	static void dfs(int idx, int count, int N, int[][] map, boolean[] visited) {
		if(count == N / 2) {
			calculator(N, map, visited);
			return;
		}

		for(int i = idx; i < N; i++) {
			visited[i] = true;
			dfs(i + 1, count + 1, N, map, visited);
			visited[i] = false;
		}
	}

	static void calculator(int N, int[][] map, boolean[] visited) {
		int start = 0;
		int link = 0;

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visited[i] && visited[j]) {
					start += map[i][j] + map[j][i];
				} else if (!visited[i] && !visited[j]) {
					link += map[i][j] + map[j][i];
				}
			}
		}
		answer = Math.min(answer, Math.abs(start - link));
	}
}