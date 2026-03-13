import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int  j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] commands = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			commands[i] = Integer.parseInt(st.nextToken());
		}

		int[] dx = {0, 0, 0, -1, 1};
		int[] dy = {0, 1, -1, 0, 0};
		int[] dice = new int[6];

		StringBuilder sb = new StringBuilder();

		for(int cmd : commands) {
			int nx = x + dx[cmd];
			int ny = y + dy[cmd];

			if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			x = nx;
			y = ny;

			roll(dice, cmd);

			if (map[x][y] == 0) {
				map[x][y] = dice[1];
			} else {
				dice[1] = map[x][y];
				map[x][y] = 0;
			}

			sb.append(dice[0]).append("\n");
		}
		System.out.print(sb);
	}

	static void roll(int[] dice, int dir) {
		int top = dice[0];
		int bottom = dice[1];
		int north = dice[2];
		int south = dice[3];
		int west = dice[4];
		int east = dice[5];

		if (dir == 1) {
			dice[0] = west;
			dice[1] = east;
			dice[4] = bottom;
			dice[5] = top;
		} else if (dir == 2) {
			dice[0] = east;
			dice[1] = west;
			dice[4] = top;
			dice[5] = bottom;
		} else if (dir == 3) {
			dice[0] = south;
			dice[1] = north;
			dice[2] = top;
			dice[3] = bottom;
		} else {
			dice[0] = north;
			dice[1] = south;
			dice[2] = bottom;
			dice[3] = top;
		}
	}
}