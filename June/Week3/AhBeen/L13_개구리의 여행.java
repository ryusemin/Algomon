import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static char[][] map;

	static class Frog implements Comparable<Frog> {
		int x;
		int y;
		int k;
		int time;

		Frog(int x, int y, int k, int time) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.time = time;
		}

		@Override
		public int compareTo(Frog o) {
			return Integer.compare(this.time, o.time);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N + 1][N + 1];

		for(int i = 1; i <= N; i++) {
			String line = br.readLine();
			for(int j = 1; j <= N; j++) {
				map[i][j] = line.charAt(j - 1);
			}
		}

		int Q = Integer.parseInt(br.readLine());

		for(int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());

			int time = dijkstra(r1, c1, r2, c2);

			System.out.println(time);
		}
	}

	static int dijkstra(int r1, int c1, int r2, int c2) {
		PriorityQueue<Frog> pq = new PriorityQueue<>();
		boolean[][][] visited = new boolean[N + 1][N + 1][6];
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		pq.add(new Frog(r1, c1, 1, 0));

		while(!pq.isEmpty()) {
			Frog cur = pq.poll();

			if(cur.x == r2 && cur.y == c2) {
				return cur.time;
			}

			if(visited[cur.x][cur.y][cur.k]) continue;

			visited[cur.x][cur.y][cur.k] = true;

			for(int d = 0; d < 4; d++) {
				boolean canJ = true;

				for(int i = 1; i <= cur.k; i++) {
					int nx = cur.x + dx[d] * i;
					int ny = cur.y + dy[d] * i;

					if(nx < 1 || ny < 1 || nx > N || ny > N) {
						canJ = false;
						break;
					}

					if(map[nx][ny] == '#') {
						canJ = false;
						break;
					}

					if(i == cur.k && map[nx][ny] == 'S') {
						canJ = false;
						break;
					}
				}

				if(canJ) {
					int nx = cur.x + dx[d] * cur.k;
					int ny = cur.y + dy[d] * cur.k;

					pq.add(new Frog(nx, ny, cur.k, cur.time + 1));
				}
			}

			if(cur.k < 5) {
				int nk = cur.k + 1;

				if(!visited[cur.x][cur.y][nk]) {
					pq.add(new Frog(cur.x, cur.y, nk, cur.time + nk * nk));
				}
			}

			for(int nk = cur.k; nk > 0; nk--) {
				if(!visited[cur.x][cur.y][nk]) {
					pq.add(new Frog(cur.x, cur.y, nk, cur.time + 1));
				}
			}
		}

		return -1;
	}
}