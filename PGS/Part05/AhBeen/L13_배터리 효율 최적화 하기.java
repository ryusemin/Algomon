import java.util.*;

public class Main {
	static int N, M, NM;
	static int[][] grid;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static Set<Integer> visitedMasks = new HashSet<>();
	static List<Integer> masks = new ArrayList<>();
	static List<Integer> sums = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		NM = N * M;
		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				grid[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int startIdx = i * M + j;
				List<Integer> init = new ArrayList<>();
				init.add(startIdx);
				dfs(init, grid[i][j], 1 << startIdx);
			}
		}

		int ans = Integer.MIN_VALUE;
		int size = masks.size();
		for (int i = 0; i < size; i++) {
			int maskA = masks.get(i);
			int sumA = sums.get(i);
			for (int j = i + 1; j < size; j++) {
				int maskB = masks.get(j);
				int overlap = maskA & maskB;
				if (Integer.bitCount(overlap) == 2) {
					ans = Math.max(ans, sumA + sums.get(j));
				}
			}
		}

		System.out.println(ans);
	}

	static void dfs(List<Integer> cells, int sum, int mask) {
		if (cells.size() == 5) {
			if (!visitedMasks.contains(mask)) {
				visitedMasks.add(mask);
				masks.add(mask);
				sums.add(sum);
			}
			return;
		}

		Set<Integer> nextCandidates = new HashSet<>();
		for (int idx : cells) {
			int x = idx / M;
			int y = idx % M;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				int ni = nx * M + ny;
				if ((mask & (1 << ni)) != 0) continue;
				nextCandidates.add(ni);
			}
		}

		for (int ni : nextCandidates) {
			cells.add(ni);
			dfs(cells, sum + grid[ni / M][ni % M], mask | (1 << ni));
			cells.remove(cells.size() - 1);
		}
	}
}

