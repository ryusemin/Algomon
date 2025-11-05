import java.util.*;
import java.io.*;

public class Main {
	static int[] answer = new int[6];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int k = Integer.parseInt(st.nextToken());
			if(k == 0) {
				break;
			}
			int[] arr = new int[k];
			for(int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			boolean[] visited = new boolean[k];
			dfs(0, 0, k, arr, visited);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	static void dfs(int start, int depth, int k, int[] arr, boolean[] visited) {
		if(depth == 6) {
			for(int num : answer) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = start; i < k; i++) {
			if(!visited[i]) {
				visited[i] = true;
				answer[depth] = arr[i];
				dfs(i, depth + 1, k, arr, visited);
				visited[i] = false;
			}
		}
	}
}