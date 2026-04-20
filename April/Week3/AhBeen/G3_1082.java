import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] cost = new int[N];

		int minCost = Integer.MAX_VALUE;
		int idx = -1;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			cost[i] = Integer.parseInt(st.nextToken());

			if(minCost >= cost[i]) {
				minCost = cost[i];
				idx = i;
			}
		}

		int M = Integer.parseInt(br.readLine());

		ArrayList<Integer> result = new ArrayList<>();
		result.add(idx);
		M -= cost[idx];

		while(M >= minCost) {
			result.add(idx);
			M -= minCost;
		}

		int start = 0;
		for(int i = 0; i < result.size(); i++) {
			for(int d = N - 1; d >= 0; d--) {
				int cur = result.get(i);

				if(d <= cur) continue;

				int extra = cost[d] - cost[cur];

				if(extra <= M) {
					result.set(i, d);
					M -= extra;
					break;
				}
			}

			if(result.get(start) == 0) {
				start++;
				M += minCost;
			}
		}

		if(start == result.size()) {
			System.out.println(0);
			System.exit(0);
		}

		StringBuilder sb = new StringBuilder();
		for (int num : result) {
			if(sb.length() == 0 && num == 0) continue;
			sb.append(num);
		}
		System.out.println(sb);
	}
}