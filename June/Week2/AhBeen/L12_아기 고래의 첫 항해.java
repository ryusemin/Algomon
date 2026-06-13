import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int r;
	static int c;
	static int d;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};

	static int reefCount = 0;
	static int canMoveCount = 0;

	static List<Node> result = new ArrayList<>();

	static class Node {
		int x;
		int y;
		int dir;
		int dist;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Node(int x, int y, int dir, int dist) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if(map[i][j] == 1) reefCount++;
			}
		}

		result.add(new Node(r, c));
		visited[r][c] = true;

		canMoveCount = N * N - reefCount;

		while(result.size() < canMoveCount) {
			explore();
		}

		for(Node n : result) {
			System.out.println(n.x + " " + n.y);
		}
	}

	//이동 후 r, c 최신화
	// 우선순위대로 탐색
	static void explore() {
		int dir = d;

		int nr = r + dr[dir];
		int nc = c + dc[dir];

		if(nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
			if(map[nr][nc] == 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				result.add(new Node(nr, nc));
				r = nr;
				c = nc;
				d = dir;
				return;
			}
		}

		dir = rotateLeft(d);

		nr = r + dr[dir];
		nc = c + dc[dir];

		if(nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
			if(map[nr][nc] == 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				result.add(new Node(nr, nc));
				r = nr;
				c = nc;
				d = dir;
				return;
			}
		}

		dir = rotateRight(d);
		nr = r + dr[dir];
		nc = c + dc[dir];

		if(nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
			if(map[nr][nc] == 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				result.add(new Node(nr, nc));
				r = nr;
				c = nc;
				d = dir;
				return;
			}
		}

		dir = rotateOpposition(d);
		nr = r + dr[dir];
		nc = c + dc[dir];

		if(nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
			if(map[nr][nc] == 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				result.add(new Node(nr, nc));
				r = nr;
				c = nc;
				d = dir;
				return;
			}
		}

		//갈 곳이 없는 경우
		move();
	}

	static int rotateLeft(int curDir) {

		switch(curDir) {
			case 1: return 3;
			case 2: return 4;
			case 3: return 2;
			case 4: return 1;
		}

		return curDir;
	}

	static int rotateRight(int curDir) {
		switch(curDir) {
			case 1: return 4;
			case 2: return 3;
			case 3: return 1;
			case 4: return 2;
		}

		return curDir;
	}

	static int rotateOpposition(int curDir) {
		switch(curDir) {
			case 1: return 2;
			case 2: return 1;
			case 3: return 4;
			case 4: return 3;
		}

		return curDir;
	}

	// 이동 가능한 곳 중 가장 가까운 곳으로 이동
	// 근데 이동 시 암초가 없어야하기 때문에 bfs를 통해 가장 가까운 곳 찾기
	// 이동 방향과 함께 계산
	static void move() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[][] moveV = new boolean[N + 1][N + 1];

		List<Node> candidates = new ArrayList<>();

		q.add(new Node(r, c, d, 0));
		moveV[r][c] = true;

		int minDist = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Node cur = q.poll();

			if(cur.dist > minDist) break;

			if(map[cur.x][cur.y] == 0 && !visited[cur.x][cur.y]) {
				minDist = cur.dist;
				candidates.add(cur);
			}

			for(int dir = 1; dir <= 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if(nr < 1 || nc < 1 || nr > N || nc > N) continue;
				if(moveV[nr][nc] || map[nr][nc] == 1) continue;

				moveV[nr][nc] = true;
				q.add(new Node(nr, nc, dir, cur.dist + 1));
			}
		}

		if(candidates.isEmpty()) {
			canMoveCount = 0;
			return;
		}

		Collections.sort(candidates, (a, b) -> {
			if(a.x != b.x) return Integer.compare(a.x, b.x);
			return Integer.compare(a.y, b.y);
		});
		Node target = candidates.get(0);


		int[][] distToTarget = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) Arrays.fill(distToTarget[i], -1);

		Queue<Node> rq = new ArrayDeque<>();
		rq.add(new Node(target.x, target.y));
		distToTarget[target.x][target.y] = 0;

		while(!rq.isEmpty()) {
			Node cur = rq.poll();
			for(int dir = 1; dir <= 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if(nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
					if(map[nr][nc] == 0 && distToTarget[nr][nc] == -1) {
						distToTarget[nr][nc] = distToTarget[cur.x][cur.y] + 1;
						rq.add(new Node(nr, nc));
					}
				}
			}
		}

		int currR = r;
		int currC = c;
		int lastDir = d;
		int[] movePriority = {3, 2, 4, 1};

		while(currR != target.x || currC != target.y) {
			for(int dir : movePriority) {
				int nr = currR + dr[dir];
				int nc = currC + dc[dir];

				if(nr >= 1 && nc >= 1 && nr <= N && nc <= N && map[nr][nc] == 0) {
					if(distToTarget[nr][nc] == distToTarget[currR][currC] - 1) {
						currR = nr;
						currC = nc;
						lastDir = dir;
						break;
					}
				}
			}
		}

		r = target.x;
		c = target.y;
		d = lastDir;

		visited[r][c] = true;
		result.add(new Node(r, c));
	}
}