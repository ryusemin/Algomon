import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] score;
	static boolean[][] visited;

	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		score = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		int r = 0;
		int c = 0;
		int dir = 0;
		int[] dice = {1, 6, 2, 5, 4, 3};

		int answer = 0;
		for (int turn = 0; turn < K; turn++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
				dir = (dir + 2) % 4;
				nr = r + dr[dir];
				nc = c + dc[dir];
			}

			r = nr;
			c = nc;

			roll(dice, dir);

			answer += score[r][c];

			int A = dice[1];
			int B = map[r][c];

			if (A > B) {
				dir = (dir + 1) % 4;
			} else if (A < B) {
				dir = (dir + 3) % 4;
			}
		}
		System.out.print(answer);
	}

	static void bfs(int sr, int sc) {
		int num = map[sr][sc];
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> cells = new ArrayList<>();

		q.add(new int[]{sr, sc});
		visited[sr][sc] = true;
		cells.add(new int[]{sr, sc});

		int count = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (visited[nr][nc] || map[nr][nc] != num) continue;

				visited[nr][nc] = true;
				q.add(new int[]{nr, nc});
				cells.add(new int[]{nr, nc});
				count++;
			}
		}

		int s = count * num;
		for (int[] cell : cells) {
			score[cell[0]][cell[1]] = s;
		}
	}

	static void roll(int[] dice, int dir) {
		int top = dice[0];
		int bottom = dice[1];
		int north = dice[2];
		int south = dice[3];
		int west = dice[4];
		int east = dice[5];

		if (dir == 0) {
			dice[0] = west;
			dice[1] = east;
			dice[4] = bottom;
			dice[5] = top;
		} else if (dir == 1) {
			dice[0] = north;
			dice[1] = south;
			dice[2] = bottom;
			dice[3] = top;
		} else if (dir == 2) {
			dice[0] = east;
			dice[1] = west;
			dice[4] = top;
			dice[5] = bottom;
		} else {
			dice[0] = south;
			dice[1] = north;
			dice[2] = top;
			dice[3] = bottom;
		}
	}
}