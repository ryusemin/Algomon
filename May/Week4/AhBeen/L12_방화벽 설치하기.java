import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[][] map;
	static boolean[][] visited;

	static int max = Integer.MIN_VALUE;

	static int fireCnt = 0;

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
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
				if(map[i][j] == 2) {
					fireCnt++;
				}
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
				if(map[i][j] == 2 || map[i][j] == 1) continue;

				map[i][j] = 1;
				dfs(depth + 1);
				map[i][j] = 0;
			}
		}
	}

	static int fire() {
		int[][] tmp = new int[n][m];
		Queue<Node> q = new ArrayDeque<>();

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				tmp[i][j] = map[i][j];
				if(tmp[i][j] == 2) {
					q.add(new Node(i, j));
				}
			}
		}

		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		while(!q.isEmpty()) {
			Node cur = q.poll();

			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if(tmp[nx][ny] != 0) continue;

				tmp[nx][ny] = 2;

				q.add(new Node(nx, ny));
			}
		}

		return getArea(tmp);
	}

	static int getArea(int[][] tmp) {
		int area = 0;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tmp[i][j] == 0) {
					area++;
				}
			}
		}

		return area;
	}
}