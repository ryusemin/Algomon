import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;

	static int x;
	static int y;
	static int d;

	static boolean[][] visited;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		st = new StringTokenizer(br.readLine());

		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[n][m];
		visited[x][y] = true;

		dfs(x, y, d, 1);
	}

	static void dfs(int x, int y, int dir, int area) {
		int cDir = dir;
		boolean move = false;

		for(int i = 0; i < 4; i++) {
			cDir = (cDir - 1 + 4) % 4;
			int nx = x + dx[cDir];
			int ny = y + dy[cDir];

			if(map[nx][ny] == 1 || visited[nx][ny]) continue;

			visited[nx][ny] = true;
			dfs(nx, ny, cDir, area + 1);
			move = true;
			break;
		}

		if(!move) {
			int backDir = (dir + 2) % 4;
			int nx = x + dx[backDir];
			int ny = y + dy[backDir];

			if(map[nx][ny] != 1) {
				dfs(nx, ny, dir, area);
			} else {
				System.out.println(area);
				System.exit(0);
			}
		}
	}
}