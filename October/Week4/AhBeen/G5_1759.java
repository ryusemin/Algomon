import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		String[] str_arr = new String[C];
		for(int i = 0; i < C; i++) {
			str_arr[i] = st.nextToken();
		}
		Arrays.sort(str_arr);

		dfs(str_arr, L, 0, "", 0, 0);
	}

	static void dfs(String[] str_arr, int L, int start, String code, int mo, int ja) {
		if(code.length() == L) {
			if(mo >= 1 && ja >= 2) {
				System.out.println(code);
			}
			return;
		}
		for(int i = start; i < str_arr.length; i++) {
			String s = str_arr[i];
			boolean isMo = isMo(s.charAt(0));
			dfs(str_arr, L, i + 1, code + s, mo + (isMo ? 1 : 0), ja + (isMo ? 0 : 1));
		}
	}
	static boolean isMo(char c) {
		return "aeiou".indexOf(c) != -1;
	}
}
