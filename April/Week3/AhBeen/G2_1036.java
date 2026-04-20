import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

	static final BigInteger THIRTY_SIX = BigInteger.valueOf(36);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		String[] numbers = new String[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = br.readLine();
		}

		int K = Integer.parseInt(br.readLine());

		BigInteger total = BigInteger.ZERO;
		BigInteger[] gains = new BigInteger[36];
		Arrays.fill(gains, BigInteger.ZERO);

		for (String num : numbers) {
			BigInteger place = BigInteger.ONE;

			for (int i = num.length() - 1; i >= 0; i--) {
				int value = charToValue(num.charAt(i));

				total = total.add(place.multiply(BigInteger.valueOf(value)));

				int diff = 35 - value;
				if (diff > 0) {
					gains[value] = gains[value].add(place.multiply(BigInteger.valueOf(diff)));
				}

				place = place.multiply(THIRTY_SIX);
			}
		}

		Arrays.sort(gains, (a, b) -> b.compareTo(a));

		for (int i = 0; i < K; i++) {
			total = total.add(gains[i]);
		}

		System.out.println(to36(total));
	}

	static int charToValue(char c) {
		if ('0' <= c && c <= '9') {
			return c - '0';
		}
		return c - 'A' + 10;
	}

	static char valueToChar(int v) {
		if (0 <= v && v <= 9) {
			return (char) ('0' + v);
		}
		return (char) ('A' + (v - 10));
	}

	static String to36(BigInteger num) {
		if (num.equals(BigInteger.ZERO)) {
			return "0";
		}

		StringBuilder sb = new StringBuilder();

		while (num.compareTo(BigInteger.ZERO) > 0) {
			BigInteger[] divRem = num.divideAndRemainder(THIRTY_SIX);
			sb.append(valueToChar(divRem[1].intValue()));
			num = divRem[0];
		}

		return sb.reverse().toString();
	}
}