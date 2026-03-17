import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		boolean[] alpha = new boolean[26];
		xx: for (int i = 0; i < N; i++) {
			String str = br.readLine();
			String[] input = str.split(" "); 

			for (int j = 0; j < input.length; j++) {
				int a = input[j].charAt(0)-'A'; 
				if(a >= 26) a -= 32;
				if (!alpha[a]) {
					alpha[a] = true;
					StringBuilder sb = new StringBuilder();
					sb.append(input[j]);
					sb.insert(0, '[');
					sb.insert(2, ']');
					input[j] = sb.toString();
					for (int k = 0; k < input.length; k++) {
						ans.append(input[k]).append(' ');
					}
					ans.append('\n');
					continue xx;
				}
			}

			for (int j = 0; j < input.length; j++) {
				for (int k = 1; k < input[j].length(); k++) {
					int a = input[j].charAt(k)-'A';
					if(a > 26) a -= 32;
					if (!alpha[a]) { 
						alpha[a] = true;
						StringBuilder sb = new StringBuilder();
						sb.append(input[j]);
						sb.insert(k, '[');
						sb.insert(k + 2, ']');
						input[j] = sb.toString();
						for (int l = 0; l < input.length; l++) {
							ans.append(input[l]).append(' ');
						}
						ans.append('\n');
						continue xx;
					}
				}
			}

			for (int l = 0; l < input.length; l++) {
				ans.append(input[l]).append(' ');
			}
			ans.append('\n');
		}
		System.out.println(ans.toString());
	}

}