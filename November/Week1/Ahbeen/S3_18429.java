import java.util.*;
import java.io.*;

public class Main {
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] visited = new boolean[N];
		dfs(N, K, 0, 500, visited, arr);

		System.out.println(count);
	}

	static void dfs(int N, int K, int depth, int weight, boolean[] visited, int[] arr) {
		if(depth == N) {
			count++;
			return;
		}
		for(int i = 0; i < N; i++) {
			if(!visited[i] && (weight + arr[i] - K) >= 500) {
				visited[i] = true;
				int nWeight = weight + arr[i] - K;
				dfs(N, K, depth + 1, nWeight, visited, arr);
				visited[i] = false;
			}
		}
	}
}