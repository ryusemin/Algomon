import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H;
	static int[][][] box;
	static int[] dr = {-1, 1, 0, 0, 0 ,0};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[H][N][M];
		Queue<int[]> q = new ArrayDeque<>();
		for(int i = 0 ; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if (box[i][j][k] == 1) {
						q.add(new int[]{i, j ,k});
					}
				}
			}
		}


		bfs(q);

		int answer = 0;
		for(int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (box[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					answer = Math.max(answer, box[i][j][k]);
				}
			}
		}
		System.out.println(answer - 1);
	}

	static void bfs(Queue<int[]> q) {
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int h = cur[0];
			int r = cur[1];
			int c = cur[2];

			for (int d = 0; d < 6; d++) {
				int nh = h + dh[d];
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nh < 0 || nr < 0 || nc < 0 || nh >= H || nr >= N || nc >= M) continue;
				if (box[nh][nr][nc] != 0) continue;

				box[nh][nr][nc] = box[h][r][c] + 1;
				q.add(new int[]{nh, nr, nc});
			}
		}
	}
}