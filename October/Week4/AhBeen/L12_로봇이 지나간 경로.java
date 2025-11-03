import java.util.*;
import java.io.*;

public class Main {
	static int H;
	static int W;
	static char[][] grid;
	static boolean[][] visited;
	static String commands = null;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static char[] dirChar = {'^', '>', 'v', '<'};

	static int totalVisited = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		grid = new char[H][W];
		visited = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			String line = br.readLine();
			for (int j = 0; j < W; j++) {
				grid[i][j] = line.charAt(j);
				if (grid[i][j] == '#') totalVisited++;
			}
		}

		int sy = -1, sx = -1, sDir = -1;
		for (int i = H - 1; i >= 0; i--) {
			for (int j = W - 1; j >= 0; j--) {
				if (grid[i][j] != '#') continue;
				int count = 0, dir = -1;
				for (int k = 0; k < 4; k++) {
					int ny = i + dy[k];
					int nx = j + dx[k];
					if (ny >= 0 && ny < H && nx >= 0 && nx < W && grid[ny][nx] == '#') {
						count++;
						dir = k;
					}
				}
				if (count == 1) { // 끝점 후보
					sy = i;
					sx = j;
					sDir = dir;
					break;
				}
			}
			if (sy != -1) break;
		}

		visited[sy][sx] = true;
		dfs(sy, sx, sDir, new StringBuilder(), 1);

		System.out.println((sy + 1) + " " + (sx + 1));
		System.out.println(dirChar[sDir]);
		System.out.println(commands);
	}

	static void dfs(int y, int x, int dir, StringBuilder cmds, int visitedCount) {
		if (visitedCount == totalVisited) {
			String result = cmds.toString();
			if (commands == null || result.length() < commands.length()) {
				commands = result;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int ny1 = y + dy[i];
			int nx1 = x + dx[i];
			int ny2 = y + dy[i]*2;
			int nx2 = x + dx[i]*2;

			// 두 칸 이동 가능 여부
			if (ny2 < 0 || ny2 >= H || nx2 < 0 || nx2 >= W) continue;
			if (grid[ny1][nx1] != '#' || grid[ny2][nx2] != '#') continue;
			if (visited[ny1][nx1] || visited[ny2][nx2]) continue;

			// 방향 회전 계산
			int turn = (i - dir + 4) % 4;
			StringBuilder newCmds = new StringBuilder(cmds);
			if (turn == 1) newCmds.append("R");
			else if (turn == 2) { newCmds.append("RR"); }
			else if (turn == 3) newCmds.append("L");

			newCmds.append("A");

			// 방문 처리
			visited[ny1][nx1] = true;
			visited[ny2][nx2] = true;

			dfs(ny2, nx2, i, newCmds, visitedCount + 2);

			// 백트래킹
			visited[ny1][nx1] = false;
			visited[ny2][nx2] = false;
		}
	}
}
