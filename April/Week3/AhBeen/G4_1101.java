import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] box;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		box = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Integer.MAX_VALUE;

		answer = Math.min(answer, calculate(-1));

		for (int joker = 0; joker < N; joker++) {
			answer = Math.min(answer, calculate(joker));
		}

		System.out.println(answer);
	}

	static int calculate(int joker) {
		boolean[] usedColor = new boolean[M];
		int move = 0;

		for (int i = 0; i < N; i++) {
			int colorCount = 0;
			int colorIdx = -1;

			for (int c = 0; c < M; c++) {
				if (box[i][c] > 0) {
					colorCount++;
					colorIdx = c;
				}
			}

			if (colorCount == 0) continue;

			if (i == joker) {
				continue;
			}

			if (colorCount == 1 && !usedColor[colorIdx]) {
				usedColor[colorIdx] = true;
			} else {
				move++;
			}
		}

		return move;
	}
}