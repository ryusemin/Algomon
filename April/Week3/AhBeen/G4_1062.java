import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static String[] words;
	static boolean[] learned;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new String[N];

		for(int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		if(K < 5) {
			System.out.println(0);
			return;
		}

		if(K == 26) {
			System.out.println(N);
			return;
		}

		learned = new boolean[26];

		learned['a' - 'a'] = true;
		learned['n' - 'a'] = true;
		learned['t' - 'a'] = true;
		learned['i' - 'a'] = true;
		learned['c' - 'a'] = true;

		dfs(0, 0);
		System.out.println(answer);
	}

	static void dfs(int start, int count) {
		if(count == K - 5) {
			int readable = countReadable();
			answer = Math.max(answer, readable);
			return;
		}
		for(int i = start; i < 26; i++) {
			if(learned[i]) continue;

			learned[i] = true;
			dfs(i + 1, count + 1);
			learned[i] = false;
		}
	}

	static int countReadable() {
		int count = 0;

		for(String word : words) {
			boolean canRead = true;

			for(int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);

				if(!learned[ch - 'a']) {
					canRead = false;
					break;
				}
			}
			if(canRead) count++;
		}
		return count;
	}
}