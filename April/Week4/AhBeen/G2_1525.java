import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String goal = "123456780";

		Queue<String> q = new ArrayDeque<>();
		Map<String, Integer> dist = new HashMap<>();

		StringBuilder sb = new StringBuilder();
		String start;

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				sb.append(st.nextToken());
			}
		}

		start = sb.toString();

		q.add(start);
		dist.put(start, 0);
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		while(!q.isEmpty()) {
			String cur = q.poll();
			if (cur.equals(goal)) {
				System.out.println(dist.get(cur));
				return;
			}

			int idx = cur.indexOf('0');
			int x = idx / 3;
			int y = idx % 3;

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

				int nIdx = nx * 3 + ny;
				char[] arr = cur.toCharArray();
				String next = swap(idx, nIdx, arr);

				if(dist.containsKey(next)) continue;

				q.add(next);
				dist.put(next, dist.get(cur) + 1);
			}
		}

		System.out.println(-1);
	}

	static String swap(int idx, int nIdx, char[] arr) {
		char tmp = arr[idx];
		arr[idx] = arr[nIdx];
		arr[nIdx] = tmp;

		return new String(arr);
	}
}