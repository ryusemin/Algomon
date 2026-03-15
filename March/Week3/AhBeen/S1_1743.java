import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][M + 1];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		int answer = 0;
		boolean[][] visited = new boolean[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					answer = Math.max(answer, bfs(i, j, map, N, M, visited));
				}
			}
		}

		System.out.println(answer);
	}

	static int bfs(int r, int c, int[][] map, int N, int M, boolean[][] visited) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{r, c});
		visited[r][c] = true;

		int count = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];

				if (nr < 1 || nc < 1 || nr > N || nc > M) continue;
				if (visited[nr][nc] || map[nr][nc] == 0) continue;

				visited[nr][nc] = true;
				q.add(new int[]{nr, nc});
				count++;
			}
		}

		return count;
	}
}