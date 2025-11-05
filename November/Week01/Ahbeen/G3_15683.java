import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][] copyMap;
	static int[] output;

	static ArrayList<Node> cctvList = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	static int[][][] dirSet = {
		{},
		{{0}, {1}, {2}, {3}},
		{{0, 2}, {1, 3}}, {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
		{{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}},
		{{0, 1, 2, 3}}
	};

	static class Node {
		int num;
		int x;
		int y;
		public Node(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new Node(map[i][j], i, j));
				}
			}
		}
		output = new int[cctvList.size()];
		permutation(0);

		System.out.println(answer);
	}

	static void permutation(int depth) {
		if(depth == cctvList.size()) {
			simulate();
			return;
		}

		int num = cctvList.get(depth).num;
		for(int i = 0; i < dirSet[num].length; i++) {
			output[depth] = i;
			permutation(depth + 1);
		}
	}

	static void simulate() {
		copyMap = new int[N][M];
		for(int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		for(int i = 0; i < cctvList.size(); i++) {
			Node cctv = cctvList.get(i);
			int idx = output[i];
			for(int d : dirSet[cctv.num][idx]) {
				watch(cctv.x, cctv.y, d);
			}
		}
		countBlindSpot();
	}
	static void watch(int x, int y, int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];

		while (nx >= 0 && nx < N && ny >= 0 && ny < M && copyMap[nx][ny] != 6) {
			if (copyMap[nx][ny] == 0) copyMap[nx][ny] = -1;
			nx += dx[d];
			ny += dy[d];
		}
	}

	static void countBlindSpot() {
		int count = 0;
		for(int[] cm : copyMap) {
			for(int v : cm) {
				if(v == 0) count++;
			}
		}
		answer = Math.min(answer, count);
	}
}