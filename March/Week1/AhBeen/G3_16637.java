import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String exp;
	static List<Integer> numList = new ArrayList<>();
	static List<Character> opList = new ArrayList<>();
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		exp = br.readLine();

		for(int i = 0; i < N; i++) {
			char ch = exp.charAt(i);
			if(i % 2 == 0) {
				numList.add(ch - '0');
			} else {
				opList.add(ch);
			}
		}

		dfs(0, numList.get(0));
		System.out.println(answer);
	}

	static void dfs(int opIdx, int cur) {
		if(opIdx >= opList.size()) {
			answer = Math.max(answer, cur);
			return;
		}

		int result1 = calc(cur, numList.get(opIdx + 1), opList.get(opIdx));
		dfs(opIdx + 1, result1);

		if(opIdx + 1 < opList.size()) {
			int bracket = calc(numList.get(opIdx + 1), numList.get(opIdx + 2), opList.get(opIdx + 1));
			int result2 = calc(cur, bracket, opList.get(opIdx));
			dfs(opIdx + 2, result2);
		}
	}

	static int calc(int a, int b, char op) {
		if (op == '+') return a + b;
		if (op == '-') return a - b;
		return a * b;
	}

}