import java.io.*;
import java.util.*;

public class Main {
	static int[][] map = new int[9][9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
	}

	static void dfs(int x, int y) {
		if(y == 9) {
			dfs(x + 1, 0);
			return;
		}

		if(x == 9) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
		}

		if(map[x][y] == 0) {
			for(int i = 1; i < 10; i++) {
				if(check(x, y, i)) {
					map[x][y] = i;
					dfs(x, y + 1);
				}
			}
			map[x][y] = 0;
			return;
		}

		dfs(x, y + 1);
	}

	static boolean check(int x, int y, int num) {
		for(int i = 0; i < 9; i++) {
			if(map[x][i] == num) {
				return false;
			}
		}

		for(int i = 0; i < 9; i++) {
			if(map[i][y] == num) {
				return false;
			}
		}

		int xx = x / 3 * 3;
		int yy = y / 3 * 3;

		for(int i = xx; i < xx + 3; i++) {
			for(int j = yy ; j < yy  + 3; j++) {
				if(map[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}
}