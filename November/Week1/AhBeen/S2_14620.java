import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		answer = dfs(N, 0, 0, answer, visited, map);

		System.out.println(answer);
	}

	static int dfs(int N, int depth, int cost, int answer, boolean[][] visited, int[][] map) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		if (depth == 3) {
			return Math.min(answer, cost);
		}
		//끝에서 시작하면 넘어가버림]
		for(int i = 1; i < N - 1; i++) {
			for(int j = 1; j < N - 1; j++) {
				if(visited[i][j]) {
					continue;
				}
				boolean chk = true;
				for(int k = 0; k < 4; k++){
					int nx = i + dx[k];
					int ny = j + dy[k];

					if(visited[nx][ny]) {
						chk = false;
						continue;
					}
				}
				if(!chk) {
					continue;
				}
				int sum = map[i][j];
				visited[i][j] = true;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					sum += map[nx][ny];
					visited[nx][ny] = true;
				}

				answer = dfs(N, depth + 1, cost + sum, answer, visited, map);

				visited[i][j] = false;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					visited[nx][ny] = false;
				}
			}
		}
		return answer;
	}
}