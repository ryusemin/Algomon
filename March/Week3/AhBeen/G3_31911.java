import java.io.*;
import java.util.*;

public class Main {
	static final int CHAR_CNT = 29;

	static int toIdx(char ch) {
		if(ch == '-') return 0;
		if(ch == '[') return 1;
		if(ch == ']') return 2;
		return ch - 'a' + 3;
	}

	static char toChar(int idx) {
		if(idx == 0) return '-';
		if(idx == 1) return '[';
		if(idx == 2) return ']';
		return (char) ('a' + (idx - 3));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] count = new int[CHAR_CNT][CHAR_CNT];

		for (int i = 0; i < N; i++) {
			String s = br.readLine().trim();
			for(int j = 0; j + 1 < s.length(); j++) {
				int from = toIdx(s.charAt(j));
				int to = toIdx(s.charAt(j + 1));
				count[from][to]++;
			}
		}

		int[] next = new int[CHAR_CNT];
		Arrays.fill(next, -1);

		for(int i = 0; i < CHAR_CNT; i++) {
			int bestCount = -1;
			int bestIdx = -1;
			for(int j = 0; j < CHAR_CNT; j++) {
				if(count[i][j] > bestCount) {
					bestCount = count[i][j];
					bestIdx = j;
				}
			}
			next[i] = bestIdx;
		}

		StringBuilder seq = new StringBuilder();
		seq.append('[');

		int[] firstPos = new int[CHAR_CNT];
		Arrays.fill(firstPos, -1);
		firstPos[toIdx('[')] = 0;

		boolean finite = false;
		int cycleStart = -1;

		while(true) {
			int cur = toIdx(seq.charAt(seq.length() - 1));
			int nx = next[cur];

			if(nx == -1) {
				finite = true;
				break;
			}

			char ch = toChar(nx);

			if(ch == ']') {
				seq.append(ch);
				finite = true;
				break;
			}

			if(firstPos[nx] != -1) {
				cycleStart = firstPos[nx];
				break;
			}

			firstPos[nx] = seq.length();
			seq.append(ch);
		}

		StringBuilder ans = new StringBuilder();
		for (long pos = K - 1; pos < K - 1 + M; pos++) {
			char out;

			if (finite) {
				if (pos < seq.length()) out = seq.charAt((int) pos);
				else out = '.';
			} else {
				if (pos < seq.length()) {
					out = seq.charAt((int) pos);
				} else {
					int cycleLen = seq.length() - cycleStart;
					long offset = (pos - cycleStart) % cycleLen;
					out = seq.charAt((int) (cycleStart + offset));
				}
			}

			ans.append(out);
		}

		System.out.println(ans);
	}

}