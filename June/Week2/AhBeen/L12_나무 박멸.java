import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int m;
	static int k;
	static int c;

	static Node[][] map;

	static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
	static int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};

	static int maxX;
	static int maxY;

	static class Node {
		int count;
		int c;

		Node(int count, int c) {
			this.count = count;
			this.c = c;
		}
	}

	static class Tree {
		int x;
		int y;
		int count;

		Tree(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	static int total = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new Node[n][n];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int count = Integer.parseInt(st.nextToken());
				map[i][j] = new Node(count, 0);
			}
		}

		for(int i = 0; i < m; i++) {
			grow();

			breeding();

			decreaseHerbicide();

			spray();
		}

		System.out.println(total);
	}

	static void decreaseHerbicide() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j].c > 0) {
					map[i][j].c--;
				}
			}
		}
	}

	//나무 성장
	static void grow() {
		int[][] addCount = new int[n][n];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j].count > 0) {
					int count = 0;

					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if(nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny].count <= 0) continue;

						count++;
					}

					addCount[i][j] = count;
				}
			}
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j].count += addCount[i][j];
			}
		}
	}

	//번식 상하좌우
	static void breeding() {
		int[][] temp = new int[n][n];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j].count <= 0) continue;

				int count = 0;

				for(int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
					if(map[nx][ny].count != 0 || map[nx][ny].c > 0) continue;

					count++;
				}

				if(count > 0) {
					int val = map[i][j].count / count;
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
						if(map[nx][ny].count != 0 || map[nx][ny].c > 0) continue;

						temp[nx][ny] += val;
					}
				}
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j].count == 0 && map[i][j].c == 0) {
					map[i][j].count += temp[i][j];
				}
			}
		}
	}

	//제초제
	static void spray() {
		int maxKilled = -1;
		int maxX = -1;
		int maxY = -1;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int killed = 0;

				if(map[i][j].count > 0) {
					killed = map[i][j].count;

					for(int d = 4; d < 8; d++) {
						for(int l = 1; l <= k; l++) {
							int nx = i + dx[d] * l;
							int ny = j + dy[d] * l;

							if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;
							if(map[nx][ny].count <= 0) break;

							killed += map[nx][ny].count;
						}
					}
				} else {
					killed = 0;
				}

				if(killed > maxKilled) {
					maxKilled = killed;
					maxX = i;
					maxY = j;
				}
			}
		}

		total += maxKilled;

		if(map[maxX][maxY].count > 0) {
			map[maxX][maxY].count = 0;
			map[maxX][maxY].c = c;

			for(int d = 4; d < 8; d++) {
				for(int l = 1; l <= k; l++) {
					int nx = maxX + dx[d] * l;
					int ny = maxY + dy[d] * l;

					if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;

					if(map[nx][ny].count < 0) {
						break;
					} else if(map[nx][ny].count == 0) {
						map[nx][ny].c = c;
						break;
					} else {
						map[nx][ny].count = 0;
						map[nx][ny].c = c;
					}
				}
			}
		} else {
			map[maxX][maxY].c = c;
		}
	}
}
