import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int idx;
		long weight;

		Node(int idx, long weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		long[] weight = new long[26];
		boolean[] lead = new boolean[26];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			lead[str.charAt(0) - 'A'] = true;

			long place = 1;
			for (int j = str.length() - 1; j >= 0; j--) {
				weight[str.charAt(j) - 'A'] += place;
				place *= 10;
			}
		}

		int count = 0;
		for (int i = 0; i < 26; i++) {
			if (weight[i] > 0) {
				count++;
			}
		}

		int zeroIdx = -1;

		if (count == 10) {
			long minWeight = Long.MAX_VALUE;

			for (int i = 0; i < 26; i++) {
				if (weight[i] > 0 && !lead[i]) {
					if (weight[i] < minWeight) {
						minWeight = weight[i];
						zeroIdx = i;
					}
				}
			}
		}

		List<Node> list = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			if (weight[i] == 0) continue;
			if (i == zeroIdx) continue;
			list.add(new Node(i, weight[i]));
		}

		list.sort((a, b) -> Long.compare(b.weight, a.weight));

		long answer = 0;
		int num = 9;

		for (Node node : list) {
			answer += node.weight * num;
			num--;
		}

		System.out.println(answer);
	}
}