import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];

		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int answer = check(map);

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(j + 1 < N && map[i][j] != map[i][j + 1]) {
					swap(map, i, j, i, j + 1);
					answer = Math.max(answer, check(map));
					swap(map, i, j, i, j + 1);
				}

				if(i + 1 < N && map[i][j] != map[i + 1][j]) {
					swap(map, i, j, i + 1, j);
					answer = Math.max(answer, check(map));
					swap(map, i, j, i + 1, j);
				}
			}
		}

		System.out.println(answer);
	}

	static void swap(char[][] map, int x1, int y1, int x2, int y2) {
		char tmp = map[x1][y1];
		map[x1][y1] = map[x2][y2];
		map[x2][y2] = tmp;
	}

	static int check(char[][] map) {
		int best = 1;

		for(int i = 0; i < N; i++) {
			int count = 1;
			for(int j = 1; j < N; j++) {
				if(map[i][j] == map[i][j - 1]) count++;
				else count = 1;
				best = Math.max(best, count);
			}
		}

		for(int j = 0; j < N; j++) {
			int count = 1;
			for(int i = 1; i < N; i++) {
				if(map[i][j] == map[i - 1][j]) count++;
				else count = 1;
				best = Math.max(best, count);
			}
		}
		return best;
	}
}