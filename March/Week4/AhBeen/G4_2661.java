import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs("");
	}

	static void dfs(String s) {
		if (check) return;

		if(s.length() == N) {
			System.out.println(s);
			check = true;
			return;
		}

		for(char c = '1'; c <= '3'; c++) {
			String nx = s + c;

			if(check(nx)) {
				dfs(nx);
			}
		}
	}

	static boolean check(String s){
		int length = s.length();

		for(int size = 1; size <= length / 2; size++) {
			String a = s.substring(length - size);
			String b = s.substring(length - 2 * size, length - size);

			if(a.equals(b)) return false;
		}

		return true;
	}
}