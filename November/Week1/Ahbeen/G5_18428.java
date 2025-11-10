import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[][] map = new String[N][N];

		List<Node> xList = new ArrayList<>();
		List<Node> tList = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = st.nextToken();
				if(map[i][j].equals("X")) {
					xList.add(new Node(i, j));
				} else if(map[i][j].equals("T")) {
					tList.add(new Node(i, j));
				}
			}
		}
		boolean result = dfs(map, xList, tList, N, 0, 0);
		System.out.println(result ? "YES" : "NO");
	}
	static boolean dfs(String[][] map, List<Node> xList, List<Node> tList, int N, int depth, int start) {
		if(depth == 3) {
			if(check(map, tList, N)) {
				return true;
			}
			return false;
		}

		for(int i = start; i < xList.size(); i++) {
			Node node = xList.get(i);
			map[node.x][node.y] = "O";
			if(dfs(map, xList, tList, N, depth + 1, i + 1)) {
				return true;
			}
			map[node.x][node.y] = "X";
		}
		return false;
	}

	static boolean check(String[][] map, List<Node> tList, int N) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};

		for (Node n : tList) {
			for (int d = 0; d < 4; d++) {
				int nx = n.x;
				int ny = n.y;
				while (true) {
					nx += dx[d];
					ny += dy[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
						break;
					}
					if (map[nx][ny].equals("O")) {
						break;
					}
					if (map[nx][ny].equals("S")) {
						return false;
					}
				}
			}
		}
		return true;
	}
}