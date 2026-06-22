import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;

	static class Node {
		int x;
		int y;

		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);

		System.out.println(max);
	}

	static void dfs(int depth) {
		if(depth == 3) {
			max = Math.max(max, fire());
			return;
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] != 0) continue;

				map[i][j] = 1;
				dfs(depth + 1);
				map[i][j] = 0;
			}
		}
	}

	static int fire() {
		int[][] tmp = new int[n][m];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		Queue<Node> q = new ArrayDeque<>();

		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tmp[i][j] == 2) {
					q.add(new Node(i, j));
				}
			}
		}

		while(!q.isEmpty()) {
			Node cur = q.poll();

			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if(nx < 0 || ny< 0 || nx >= n || ny >= m) continue;
				if(tmp[nx][ny] != 0) continue;

				tmp[nx][ny] = 2;

				q.add(new Node(nx, ny));
			}
		}

		return getArea(tmp);
	}

	static int getArea(int[][] tmp) {
		int count = 0;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tmp[i][j] == 0) count++;
			}
		}

		return count;
	}
}