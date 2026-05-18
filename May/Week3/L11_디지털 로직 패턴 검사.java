import java.util.*;
public class Main {
	static char[] arr;
	static long MOD = 1_000_000_007;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String S = scanner.next();
		int K = scanner.nextInt();
		int M = scanner.nextInt();

		arr = S.toCharArray();
		long hash = 0;
		long power = 1;

		for (int i = 0; i < K; i++) {
			hash = (hash * 2 + (arr[i] - '0')) % MOD;
			if (i < K - 1) {
				power = (power * 2) % MOD;
			}
		}

		Map<Long, Integer> map = new HashMap<>();
		map.put(hash, 1);
		if (M == 1) {
			System.out.println(1);
			return;
		}

		for (int i = 1; i <= S.length() - K; i++) {
			long removeLeft = (arr[i - 1] - '0') * power % MOD;
			hash = (hash - removeLeft + MOD) % MOD;

			hash = (hash * 2 + (arr[i + K - 1] - '0')) % MOD;

			int count = map.getOrDefault(hash, 0) + 1;

			if (count >= M) {
				System.out.println(1);
				return;
			}

			map.put(hash, count);
		}
		System.out.println(0);
	}
}