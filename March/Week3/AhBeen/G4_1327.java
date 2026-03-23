import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		String state;
		int count;

		Node(String state, int count) {
			this.state = state;
			this.count = count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		int[] sorted = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sorted[i] = arr[i];
		}

		Arrays.sort(sorted);

		String start = toString(arr);
		String target = toString(sorted);

		System.out.println(bfs(start, target, N, K));
	}

	static int bfs(String start, String target, int N, int K) {
		Queue<Node> q = new ArrayDeque<>();
		Set<String> visited = new HashSet<>();

		q.add(new Node(start, 0));
		visited.add(start);

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.state.equals(target)) {
				return cur.count;
			}

			for (int i = 0; i <= N - K; i++) {
				String next = reverse(cur.state, i, i + K - 1);

				if (!visited.contains(next)) {
					visited.add(next);
					q.add(new Node(next, cur.count + 1));
				}
			}
		}

		return -1;
	}

	static String reverse(String s, int left, int right) {
		char[] arr = s.toCharArray();

		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}

		return new String(arr);
	}

	static String toString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int num : arr) {
			sb.append(num);
		}
		return sb.toString();
	}
}