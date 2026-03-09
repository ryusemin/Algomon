import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[][] A;
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

	static int[] diagR = {-1, -1, 1, 1};
	static int[] diagC = {-1, 1, -1, 1};
	static class Node {
		int r, c;
		Node(int r, int c) { this.r = r; this.c = c; }
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		List<Node> clouds = new ArrayList<>();
		clouds.add(new Node(N - 1, 0));
		clouds.add(new Node(N - 1, 1));
		clouds.add(new Node(N - 2, 0));
		clouds.add(new Node(N - 2, 1));

		for(int t = 0; t < M; t++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			boolean[][] wasCloud = new boolean[N][N];

			for(int i = 0; i < clouds.size(); i++) {
				Node n =  clouds.get(i);

				int nr = (n.r + dr[d] * s) % N;
				int nc = (n.c + dc[d] * s) % N;

				if (nr < 0) nr += N;
				if (nc < 0) nc += N;

				n.r = nr;
				n.c = nc;
				wasCloud[nr][nc] = true;
				A[nr][nc] += 1;
			}

			for(Node n : clouds) {
				int add = 0;
				for(int k = 0; k < 4; k++) {
					int rr = n.r + diagR[k];
					int cc = n.c + diagC[k];
					if (rr < 0 || cc < 0 || rr >= N || cc >= N) continue;
					if(A[rr][cc] > 0) add++;
				}
				A[n.r][n.c] += add;
			}

			List<Node> newClouds = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!wasCloud[i][j] && A[i][j] >= 2) {
						A[i][j] -= 2;
						newClouds.add(new Node(i, j));
					}
				}
			}
			clouds = newClouds;
		}

		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sum += A[i][j];
			}
		}
		System.out.println(sum);
	}
}