import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		int x;
		int y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static List<Node> hospitals = new ArrayList<>();
	static List<Node> people = new ArrayList<>();
	static List<Node> picks = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];



		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					people.add(new Node(i, j));
				} else if(map[i][j] == 2) {
					hospitals.add(new Node(i, j));
				}
			}
		}

		visited = new boolean[hospitals.size()];
		dfs(0, 0, m);

		System.out.println(min);
	}

	static void dfs(int start, int depth, int m) {
		if(depth == m) {
			min = Math.min(min, simulation(m));
			return;
		}

		for(int i = start; i < hospitals.size(); i++) {
			if(visited[i]) continue;
			visited[i] = true;
			picks.add(hospitals.get(i));
			dfs(i + 1, depth + 1, m);
			picks.remove(hospitals.get(i));
			visited[i] = false;
		}
	}

	static int simulation(int m) {
		int sum = 0;

		for(int i = 0; i < people.size(); i++) {
			int tmp = Integer.MAX_VALUE;
			for(int j = 0; j < m; j++) {
				tmp = Math.min(tmp, getDistance(people.get(i), picks.get(j)));
			}
			sum += tmp;
		}
		return sum;
	}
	static int getDistance(Node a, Node b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}