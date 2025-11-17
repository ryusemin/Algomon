import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int L;
	static int R;
	static int X;
	static int[] arr;
	static int count;
	int min = 0;
	int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

		System.out.println(count);
	}

	static void dfs(int depth, int sum, int min, int max, int selected) {
		if(sum > R) return;
		if(depth == N) {
			if(selected >= 2 && sum >= L && sum <= R && (max - min) >= X) {
				count++;
			}
			return;
		}
		dfs(depth + 1, sum, min, max, selected); //문제를 선택 안한 경우

		int nSum = sum + arr[depth];
		int nMin = Math.min(min, arr[depth]);
		int nMax = Math.max(max, arr[depth]);

		if(nSum <= R) {
			dfs(depth + 1, nSum, nMin, nMax, selected + 1);
		}
	}
}