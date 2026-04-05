import java.util.*;

class Solution {
	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int answer = 0;
		int[][] board = new int[102][102];

		for(int[] r : rectangle) {
			int x1 = r[0] * 2;
			int y1 = r[1] * 2;
			int x2 = r[2] * 2;
			int y2 = r[3] * 2;

			for(int x = x1; x <= x2; x++) {
				for(int y = y1; y <= y2; y++) {
					board[x][y] = 1;
				}
			}
		}
		for(int[] r : rectangle) {
			int x1 = r[0] * 2;
			int y1 = r[1] * 2;
			int x2 = r[2] * 2;
			int y2 = r[3] * 2;

			for(int x = x1 + 1; x < x2; x++) {
				for(int y = y1 + 1; y < y2; y++) {
					board[x][y] = 0;
				}
			}
		}



		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[102][102];

		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};

		q.offer(new int[]{characterX * 2, characterY * 2, 0});
		visited[characterX * 2][characterY * 2] = true;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int dist = cur[2];

			if(x == itemX * 2 && y == itemY * 2) {
				return dist / 2;
			}

			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || ny < 0 || nx >= 102 || ny >= 102) continue;

				if(visited[nx][ny] || board[nx][ny] != 1) continue;

				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, dist + 1});
			}
		}

		return answer;
	}
}