import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int m;
	static int S;
	static int T;
	static List<List<Integer>> graph;
	static List<List<Integer>> reverseGraph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		reverseGraph = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
			reverseGraph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			graph.get(x).add(y);
			reverseGraph.get(y).add(x);
		}

		st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int s = S;
		int t = T;

		boolean[] fromS = new boolean[n + 1];
		fromS[T] = true;
		dfs(S, graph, fromS);

		boolean[] toT = new boolean[n + 1];
		dfs(T, reverseGraph, toT);

		boolean[] fromT = new boolean[n + 1];
		fromT[S] = true;
		dfs(T, graph, fromT);

		boolean[] toS = new boolean[n + 1];
		dfs(S, reverseGraph, toS);

		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (i == S || i == T) continue;

			if (fromS[i] && toT[i] && fromT[i] && toS[i]) {
				count++;
			}
		}

		System.out.println(count);
	}

	static void dfs(int current, List<List<Integer>> currentGraph, boolean[] visited) {
		if (visited[current]) return;
		visited[current] = true;

		for (int next : currentGraph.get(current)) {
			dfs(next, currentGraph, visited);
		}
	}
}