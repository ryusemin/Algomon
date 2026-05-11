import java.util.*;

class Solution {
	class Node {
		int x;
		int y;
		int dist;
		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	public int solution(String[] board) {
		int answer = 0;
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		char[][] map = new char[board.length][board[0].length()];

		int eX = 0;
		int eY = 0;

		Queue<Node> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[board.length][board[0].length()];

		for(int i = 0; i < board.length; i++) {
			String str = board[i];
			for(int j = 0; j < board[0].length(); j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'G') {
					eX = i;
					eY = j;
				} else if(map[i][j] == 'R') {
					q.add(new Node(i, j, 0));
					visited[i][j] = true;
				}
			}
		}

		while(!q.isEmpty()) {
			Node cur = q.poll();

			if(cur.x == eX && cur.y == eY) {
				return cur.dist;
			}

			for(int i = 0; i < 4; i++) {
				int nx = cur.x;
				int ny = cur.y;

				while(true) {
					int x = nx + dx[i];
					int y = ny + dy[i];

					if(x < 0 || y < 0 || x >= board.length || y >= board[0].length()) break;
					if(map[x][y] == 'D') break;
					nx = x;
					ny = y;
				}

				if(visited[nx][ny]) continue;

				visited[nx][ny] = true;
				q.add(new Node(nx, ny, cur.dist + 1));
			}
		}


		return -1;
	}
}