import java.io.*;
import java.util.*;

public class Main {
	static String[] bestRowsGlobal = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] words = new String[6];
		for (int i = 0; i < 6; i++) words[i] = br.readLine().trim();

		int[] p = {0, 1, 2, 3, 4, 5};

		String bestKey = null;
		String[] bestRows = null;

		bestKey = tryCase(words, p, bestKey);
		if (bestKey != null) bestRows = bestRowsGlobal; // 아래 전역 저장 사용

		while (nextPermutation(p)) {
			String before = bestKey;
			bestKey = tryCase(words, p, bestKey);
			if (bestKey != before && bestKey != null) bestRows = bestRowsGlobal;
		}

		if (bestKey == null) {
			System.out.println(0);
		} else {
			System.out.println(bestRows[0]);
			System.out.println(bestRows[1]);
			System.out.println(bestRows[2]);
		}
	}
	static boolean nextPermutation(int[] a) {
		int i = a.length - 1;
		while (i > 0 && a[i - 1] >= a[i]) i--;
		if (i == 0) return false;

		int j = a.length - 1;
		while (a[i - 1] >= a[j]) j--;

		int tmp = a[i - 1];
		a[i - 1] = a[j];
		a[j] = tmp;

		int k = a.length - 1;
		while (i < k) {
			tmp = a[i];
			a[i] = a[k];
			a[k] = tmp;
			i++; k--;
		}
		return true;
	}
	static String tryCase(String[] words, int[] p, String bestKey) {
		String r0 = words[p[0]];
		String r1 = words[p[1]];
		String r2 = words[p[2]];

		String c0 = "" + r0.charAt(0) + r1.charAt(0) + r2.charAt(0);
		String c1 = "" + r0.charAt(1) + r1.charAt(1) + r2.charAt(1);
		String c2 = "" + r0.charAt(2) + r1.charAt(2) + r2.charAt(2);

		String[] rest = { words[p[3]], words[p[4]], words[p[5]] };
		String[] cols = { c0, c1, c2 };

		Arrays.sort(rest);
		Arrays.sort(cols);

		if (!Arrays.equals(rest, cols)) return bestKey;

		String key = r0 + r1 + r2;

		if (bestKey == null || key.compareTo(bestKey) < 0) {
			bestRowsGlobal = new String[]{r0, r1, r2};
			return key;
		}
		return bestKey;
	}
}