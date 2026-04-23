import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		Queue<Node> hedgehog = new ArrayDeque<>();
		Queue<Node> water = new ArrayDeque<>();

		char[][] map = new char[R][C];
		boolean[][] visited = new boolean[R][C];

		for(int r = 0; r < R; r++) {
			String line = br.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == 'S') {
					hedgehog.add(new Node(r, c));
					visited[r][c] = true;
				} else if(map[r][c] == '*'){
					water.add(new Node(r, c));
				}
			}
		}

		int count = 0;

		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};

		while(!hedgehog.isEmpty()) {
			int w = water.size();

			for(int i = 0; i < w; i++) {
				Node wCur = water.poll();

				for(int j = 0; j < 4; j++) {
					int nx = wCur.x + dx[j];
					int ny = wCur.y + dy[j];

					if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] != '.') continue;

					map[nx][ny] = '*';
					water.add(new Node(nx, ny));
				}
			}

			int h = hedgehog.size();

			for(int i = 0; i < h; i++) {
				Node hCur = hedgehog.poll();

				for(int j = 0; j < 4; j++) {
					int nx = hCur.x + dx[j];
					int ny = hCur.y + dy[j];

					if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

					if(map[nx][ny] == 'D') {
						count++;
						System.out.println(count);
						System.exit(0);
					}

					if(visited[nx][ny] || map[nx][ny] != '.') continue;

					visited[nx][ny] = true;
					hedgehog.add(new Node(nx, ny));
				}
			}
			count++;
		}

		System.out.println("KAKTUS");
	}
}