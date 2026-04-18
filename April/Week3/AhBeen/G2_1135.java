import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		list = new ArrayList[N];

		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if(p != -1) {
				list[p].add(i);
			}
		}
		System.out.println(dfs(0));
	}

	static int dfs(int cur) {
		if(list[cur].isEmpty()) {
			return 0;
		}

		List<Integer> times = new ArrayList<>();

		for(int nx : list[cur]) {
			times.add(dfs(nx));
		}

		Collections.sort(times, Collections.reverseOrder());

		int max = 0;

		for(int i = 0; i < times.size(); i++) {
			max = Math.max(max, times.get(i) + i + 1);
		}
		return max;
	}
}