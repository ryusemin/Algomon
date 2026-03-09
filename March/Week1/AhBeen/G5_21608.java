import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board;
	static int[] order;
	static Map<Integer, int[]> map = new HashMap<>();

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		order = new int[N * N];

		for(int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int student = Integer.parseInt(st.nextToken());
			order[i] = student;

			int[] likes = new int[4];
			for(int j = 0; j < 4; j++) {
				likes[j] = Integer.parseInt(st.nextToken());
			}
			map.put(student, likes);
		}
		for(int student : order) {
			place(student);
		}

		int answer = calculateScore();
		System.out.println(answer);
	}

	static void place(int student) {
		int bestR = -1;
		int bestC = -1;
		int bestLike = -1;
		int bestEmpty = -1;

		int[] likes = map.get(student);

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(board[r][c] != 0) {
					continue;
				}

				int likeCnt = 0;
				int emptyCnt = 0;

				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

					if (board[nr][nc] == 0) {
						emptyCnt++;
					} else if (isLike(board[nr][nc], likes)) {
						likeCnt++;
					}
				}

				if (likeCnt > bestLike ||
					(likeCnt == bestLike && emptyCnt > bestEmpty) ||
					(likeCnt == bestLike && emptyCnt == bestEmpty && r < bestR) ||
					(likeCnt == bestLike && emptyCnt == bestEmpty && r == bestR && c < bestC)) {

					bestLike = likeCnt;
					bestEmpty = emptyCnt;
					bestR = r;
					bestC = c;
				}
			}
		}
		board[bestR][bestC] = student;
	}

	static boolean isLike(int target, int[] likes) {
		for (int x : likes) {
			if (x == target) return true;
		}
		return false;
	}

	static int calculateScore() {
		int sum = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int student = board[r][c];
				int[] likes = map.get(student);

				int likeCnt = 0;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

					if (isLike(board[nr][nc], likes)) {
						likeCnt++;
					}
				}

				if (likeCnt == 1) sum += 1;
				else if (likeCnt == 2) sum += 10;
				else if (likeCnt == 3) sum += 100;
				else if (likeCnt == 4) sum += 1000;
			}
		}

		return sum;
	}
}