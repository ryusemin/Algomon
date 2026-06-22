import java.util.*;
import java.io.*;

public class Main {
	static Map<Integer, Integer> perfumeMap = new HashMap<>();
	static ArrayList<Integer> availableList = new ArrayList<>();

	static int nxId = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int Q = Integer.parseInt(br.readLine());

		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());

			if(type == 1) {
				int N = Integer.parseInt(st.nextToken());
				for(int i = 1; i <= N; i++) {
					int v = Integer.parseInt(st.nextToken());
					perfumeMap.put(nxId++, v);
				}
			} else if(type == 2) {
				int v = Integer.parseInt(st.nextToken());
				perfumeMap.put(nxId++, v);
			} else if(type == 3) {
				int idx = Integer.parseInt(st.nextToken());
				if(perfumeMap.containsKey(idx)) {
					int v = perfumeMap.get(idx);
					sb.append(v).append("\n");
					perfumeMap.remove(idx);
				} else {
					sb.append(-1).append("\n");
				}
			} else if(type == 4) {
				int K = Integer.parseInt(st.nextToken());
				updateAvailable();
				sb.append(solveBlending(K)).append("\n");
			} else if (type == 5) {
				int K = Integer.parseInt(st.nextToken());
				updateAvailable();
				sb.append(solvePerfume(K)).append("\n");
			}
		}
		System.out.print(sb);
	}

	static void updateAvailable() {
		availableList.clear();
		for(int v : perfumeMap.values()) {
			availableList.add(v);
		}
	}

	static int solveBlending(int K) {
		int[] dp = new int[K + 1];
		int INF = Integer.MAX_VALUE;

		Arrays.fill(dp, INF);
		dp[0] = 0;

		Set<Integer> uniqueValues = new HashSet<>(availableList);

		for(int v : uniqueValues) {
			for(int i = v; i <= K; i++) {
				if(dp[i - v] != INF) {
					dp[i] = Math.min(dp[i], dp[i - v] + 1);
				}
			}
		}
		return dp[K] == INF ? -1 : dp[K];
	}

	static long solvePerfume(int K) {
		long count = 0;
		int size = availableList.size();

		Collections.sort(availableList);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int sum2 = availableList.get(i) + availableList.get(j);
				int target = K - sum2;

				int idx = lowerBound(availableList, target);

				count += (size - idx);
			}
		}
		return count;
	}

	static int lowerBound(ArrayList<Integer> list, int target) {
		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = (left + right) / 2;
			if (list.get(mid) >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}