import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] part = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			part[i][0] = Integer.parseInt(st.nextToken());
			part[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(part, (a, b) -> {
			if (a[0] == b[0]) return a[1] - b[1];
			return a[0] - b[0];
		});

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(part[0][1]);

		for(int i = 1; i < n; i++) {
			if (pq.peek() <= part[i][0]) {
				pq.poll();
			}
			pq.add(part[i][1]);
		}
		System.out.println(pq.size());
	}
}