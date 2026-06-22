import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;

	static int min = Integer.MAX_VALUE;

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Vaccine {
		int x;
		int y;
		int time;

		Vaccine(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static List<Node> hospitals = new ArrayList<>();
	static boolean[] visited;
	static int totalVirus = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if(map[i][j] == 2) {
					hospitals.add(new Node(i, j));
				} else if(map[i][j] == 0) {
					totalVirus++;
				}
			}
		}
		if(totalVirus == 0) {
			System.out.println(0);
			System.exit(0);
		}
		visited = new boolean[hospitals.size()];

		dfs(0, 0);

		System.out.println(min != Integer.MAX_VALUE ? min : -1);
	}

	static void dfs(int idx, int depth) {
		if(depth == M) {
			int time = vaccine();
			if (time != -1) {
				min = Math.min(min, time);
			}
			return;
		}

		for(int i = idx; i < hospitals.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}

	static int vaccine() {
		Queue<Vaccine> q = new ArrayDeque<>();

		int[][] tmp = new int[N][N];
		boolean[][] bfsVisited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		for(int i = 0; i < visited.length; i++) {
			if(visited[i]) {
				Node h = hospitals.get(i);
				bfsVisited[h.x][h.y] = true;
				q.add(new Vaccine(h.x, h.y, 0));
			}
		}

		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		int infectVirus = 0;
		int maxTime = 0;

		while(!q.isEmpty()) {
			Vaccine cur = q.poll();

			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(bfsVisited[nx][ny]) continue;
				if(map[nx][ny] == 1) continue;

				tmp[nx][ny] = cur.time + 1;
				bfsVisited[nx][ny] = true;

				if (map[nx][ny] == 0) {
					infectVirus++;
					maxTime = cur.time + 1;
				}
				q.add(new Vaccine(nx, ny, cur.time + 1));
			}
		}

		if (infectVirus == totalVirus) {
			return maxTime;
		}
		return -1;
	}
}