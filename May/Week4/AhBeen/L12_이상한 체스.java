import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static int[][] tmp;
	static int[] output;
	static List<Node> pieceList = new ArrayList<>();

	static class Node {
		int x;
		int y;
		int num;

		Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	static int[][][] dirSet = {
		{},
		{{0}, {1}, {2}, {3}},
		{{0, 2}, {1, 3}}, {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
		{{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}},
		{{0, 1, 2, 3}}
	};

	static int answer = Integer.MAX_VALUE;

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
				if(map[i][j] != 0 && map[i][j] != 6) {
					pieceList.add(new Node(i, j, map[i][j]));
				}
			}
		}

		output = new int[pieceList.size()];

		dfs(0);

		System.out.println(answer);
	}

	static void dfs(int depth) {
		if(depth == pieceList.size()) {
			simulate();
			return;
		}

		int num = pieceList.get(depth).num;
		for(int i = 0; i < dirSet[num].length; i++) {
			output[depth] = i;
			dfs(depth + 1);
		}
	}


	static void simulate() {
		tmp = new int[n][m];

		for(int i = 0; i < n; i++) {
			tmp[i] = map[i].clone();
		}

		for(int i = 0; i < pieceList.size(); i++) {
			Node piece = pieceList.get(i);
			int idx = output[i];
			for(int d : dirSet[piece.num][idx]) {
				move(piece.x, piece.y, d);
			}
		}

		countArea();
	}

	static void move(int x, int y, int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];

		while (nx >= 0 && nx < n && ny >= 0 && ny < m && tmp[nx][ny] != 6) {
			if (tmp[nx][ny] == 0) tmp[nx][ny] = -1;
			nx += dx[d];
			ny += dy[d];
		}
	}


	static void countArea() {
		int count = 0;
		for(int[] cm : tmp) {
			for(int v : cm) {
				if(v == 0) count++;
			}
		}
		answer = Math.min(answer, count);
	}
}