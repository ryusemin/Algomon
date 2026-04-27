import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static class Node {
		int x;
		int y;
		char color;
		Node(int x, int y, char color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}
	static char[][] map = new char[12][6];
	static boolean[][] visited;
	static int[] dx = {1, -1 ,0, 0};
	static int[] dy = {0, 0, 1, -1};
	static List<Node> list;
	static Queue<Node> q;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i = 0; i < 12; i++) {
			String line = br.readLine();
			for(int j = 0; j < 6; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		while(true) {
			visited = new boolean[12][6];
			boolean exploded = false;

			for(int i = 0; i < 12; i++) {
				for(int j = 0; j < 6; j++) {
					if(map[i][j] != '.' && !visited[i][j]) {
						bfs(i, j);
						if(list.size() >= 4) {
							for(Node n : list) {
								map[n.x][n.y] = '.';
							}
							exploded = true;
						}
					}
				}
			}

			if(!exploded) break;

			gravity();
			count++;
		}

		System.out.println(count);
	}

	static void bfs(int x, int y) {
		list = new ArrayList<>();
		q = new ArrayDeque<>();

		list.add(new Node(x, y, map[x][y]));
		q.add(new Node(x, y, map[x][y]));

		visited[x][y] = true;

		while(!q.isEmpty()) {
			Node cur = q.poll();

			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if(nx < 0 || ny < 0 || nx >= 12 || ny >= 6 || visited[nx][ny]) continue;

				if(cur.color != map[nx][ny]) continue;

				list.add(new Node(nx, ny, map[nx][ny]));
				q.add(new Node(nx, ny, map[nx][ny]));
				visited[nx][ny] = true;
			}
		}
	}

	static void gravity() {

		int col = 0;

		while(col < 6) {
			List<Character> cList = new ArrayList<>();

			for(int i = 11; i >= 0; i--) {
				if(map[i][col] != '.') {
					cList.add(map[i][col]);
				}
			}

			int idx = 11;
			for(char c : cList) {
				map[idx--][col] = c;
			}

			while(idx >= 0) {
				map[idx--][col] = '.';
			}
			col++;
		}
	}
}