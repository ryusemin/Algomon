import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int n, m;

	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1};

	static List<Node> suppList = new ArrayList<>();
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

		map = new int[n][n];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int height = Integer.parseInt(st.nextToken());
				map[i][j] = height;
				if((i == n - 2 && (j == 0 || j == 1)) || (i == n - 1 && (j == 0 || j == 1))) {
					suppList.add(new Node(i, j));
				}
			}
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			for(Node n : suppList) {
				move(d, p, n);
			}

			for(Node n : suppList) {
				grow(n);
			}

			for(Node n : suppList) {
				search(n);
			}

			cut();
		}

		int total = 0;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				total += map[i][j];
			}
		}

		System.out.println(total);
	}

	static void move(int dir, int dist, Node supp) {
		for(int i = 0; i < dist; i++) {
			int nx = supp.x + dx[dir];
			int ny = supp.y + dy[dir];

			if(nx < 0) {
				nx = (supp.x - 1 + n) % n;
			}

			if(ny < 0) {
				ny = (supp.y - 1 + n) % n;
			}

			if(nx >= n) {
				nx = (supp.x + 1) % n;
			}

			if(ny >= n) {
				ny = (supp.y + 1) % n;
			}

			supp.x = nx;
			supp.y = ny;
		}
	}

	static void grow(Node supp) {
		map[supp.x][supp.y]++;
	}

	static void search(Node supp) {
		int[] r = {1, 1, -1, -1};
		int[] c = {1, -1, 1, -1};

		for(int i = 0; i < 4; i++) {
			int nx = supp.x + r[i];
			int ny = supp.y + c[i];

			if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

			if(map[nx][ny] >= 1) map[supp.x][supp.y]++;
		}
	}

	static void cut() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				boolean exist = false;

				for(Node supp : suppList) {
					if(supp.x == i && supp.y == j) {
						exist = true;
						suppList.remove(supp);
						break;
					}
				}

				if(exist) continue;

				if(map[i][j] >= 2) {
					map[i][j] -= 2;
					suppList.add(new Node(i, j));
				}
			}
		}
	}
}