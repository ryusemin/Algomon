import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		char[] arr = br.readLine().toCharArray();
		int answer = 0;

		for(int i = 0; i < N; i++) {
			if (arr[i] != 'P') continue;

			int l = Math.max(0, i - K);
			int r = Math.min(N - 1, i + K);

			for(int j = l; j <= r; j++) {
				if(arr[j] == 'H') {
					arr[j] = 'X';
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}