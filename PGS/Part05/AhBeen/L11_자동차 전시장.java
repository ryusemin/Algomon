import java.util.*;

public class Main {
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();

		for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			graph.get(a).add(b);
		}

		int[] startPoints = new int[k];
		for (int i = 0; i < k; i++) startPoints[i] = sc.nextInt() - 1;

		int[][] dist = new int[k][n];
		for (int i = 0; i < k; i++) {
			Arrays.fill(dist[i], -1);
			bfs(startPoints[i], dist[i]);
		}

		int node = -1;
		int minDist = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			int maxDist = 0;
			boolean possible = true;
			for (int j = 0; j < k; j++) {
				if (dist[j][i] == -1) {
					possible = false;
					break;
				}
				maxDist = Math.max(maxDist, dist[j][i]);
			}
			if (possible && maxDist < minDist) {
				minDist = maxDist;
				node = i + 1;
			}
		}

		System.out.println(node == -1 ? -1 : minDist);
	}

	static void bfs(int start, int[] dist) {
		Queue<Integer> q = new ArrayDeque<>();
		dist[start] = 0;
		q.add(start);

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph.get(cur)) {
				if (dist[next] == -1) {
					dist[next] = dist[cur] + 1;
					q.add(next);
				}
			}
		}
	}
}
