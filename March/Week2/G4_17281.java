import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] inningResult;
	static int[] order = new int[9];
	static boolean[] visited = new boolean[9];
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		inningResult = new int[N][9];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inningResult[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order[3] = 0;
		visited[0] = true;

		permute(0);
		System.out.println(answer);
	}

	static void permute(int depth) {
		if (depth == 9) {
			answer = Math.max(answer, simulate());
			return;
		}

		if (depth == 3) {
			permute(depth + 1);
			return;
		}

		for (int player = 1; player < 9; player++) {
			if (!visited[player]) {
				visited[player] = true;
				order[depth] = player;
				permute(depth + 1);
				visited[player] = false;
			}
		}
	}

	static int simulate() {
		int score = 0;
		int hitterIdx = 0;

		for (int inning = 0; inning < N; inning++) {
			int out = 0;
			boolean[] base = new boolean[4];

			while (out < 3) {
				int player = order[hitterIdx];
				int result = inningResult[inning][player];

				if (result == 0) {
					out++;
				} else if (result == 1) {
					if (base[3]) score++;
					base[3] = base[2];
					base[2] = base[1];
					base[1] = true;
				} else if (result == 2) {
					if (base[3]) score++;
					if (base[2]) score++;
					base[3] = base[1];
					base[2] = true;
					base[1] = false;
				} else if (result == 3) {
					if (base[3]) score++;
					if (base[2]) score++;
					if (base[1]) score++;
					base[3] = true;
					base[2] = false;
					base[1] = false;
				} else if (result == 4) {
					if (base[3]) score++;
					if (base[2]) score++;
					if (base[1]) score++;
					score++;
					base[1] = base[2] = base[3] = false;
				}

				hitterIdx = (hitterIdx + 1) % 9;
			}
		}

		return score;
	}
}