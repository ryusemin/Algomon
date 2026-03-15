import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int K;
	static int[][] origin;
	static int[][] ops;
	static boolean[] visited;
	static int[] order;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		origin = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		ops = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			ops[i][0] = Integer.parseInt(st.nextToken()) - 1; // r
			ops[i][1] = Integer.parseInt(st.nextToken()) - 1; // c
			ops[i][2] = Integer.parseInt(st.nextToken());     // s
		}

		visited = new boolean[K];
		order = new int[K];

		permute(0);
		System.out.println(answer);
	}

	static void permute(int depth) {
		if (depth == K) {
			int[][] copied = copyArray(origin);

			for (int idx : order) {
				rotate(copied, ops[idx][0], ops[idx][1], ops[idx][2]);
			}

			answer = Math.min(answer, getMinRowSum(copied));
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				permute(depth + 1);
				visited[i] = false;
			}
		}
	}

	static void rotate(int[][] arr, int r, int c, int s) {
		for (int layer = 1; layer <= s; layer++) {
			int top = r - layer;
			int left = c - layer;
			int bottom = r + layer;
			int right = c + layer;

			int temp = arr[top][left];

			for (int i = top; i < bottom; i++) {
				arr[i][left] = arr[i + 1][left];
			}
			for (int j = left; j < right; j++) {
				arr[bottom][j] = arr[bottom][j + 1];
			}
			for (int i = bottom; i > top; i--) {
				arr[i][right] = arr[i - 1][right];
			}
			for (int i = right; i > left + 1; i--) {
				arr[top][i] = arr[top][i - 1];
			}

			arr[top][left + 1] = temp;
		}
	}

	static int getMinRowSum(int[][] arr) {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += arr[i][j];
			}
			min = Math.min(min, sum);
		}
		return min;
	}

	static int[][] copyArray(int[][] arr) {
		int[][] copied = new int[N][M];
		for (int i = 0; i < N; i++) {
			copied[i] = arr[i].clone();
		}
		return copied;
	}
}