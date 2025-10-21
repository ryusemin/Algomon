import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] part = new int[n][2];
		for(int i = 0; i < n; i++) {
			part[i][0] = sc.nextInt();
			part[i][1] = sc.nextInt();
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