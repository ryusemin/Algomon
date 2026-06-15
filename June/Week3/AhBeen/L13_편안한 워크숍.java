import java.util.*;

public class Main {
	static int N;
	static int K;
	static int[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int h;

		Node(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.h, o.h);
		}
	}

	static List<Node> nodes = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		K = scanner.nextInt();

		map = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = scanner.nextInt();


		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				nodes.add(new Node(i, j, map[i][j]));
			}
		}

		Collections.sort(nodes);

		int low = 0;
		int high = 100_000_000;
		int answer = -1;

		while(low <= high) {
			int mid = (low + high) / 2;

			if(isValid(mid)) {
				answer = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(answer);
	}

	static boolean isValid(int maxDiff) {
		int[][] dp = new int[N][N];

		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 1);
		}

		for(Node cur : nodes) {
			int x = cur.x;
			int y = cur.y;
			int h = cur.h;

			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if(map[nx][ny] < h) {
						if(h - map[nx][ny] <= maxDiff) {
							dp[x][y] = Math.max(dp[x][y], dp[nx][ny] + 1);
						}
					}
				}
			}

			if(dp[x][y] >= K) {
				return true;
			}
		}

		return false;
	}
}