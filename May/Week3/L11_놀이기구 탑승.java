import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] list;
	static int[][] board;
	static int[] order;

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		list = new int[n * n + 1][5];
		board = new int[n + 1][n + 1];
		order = new int[n * n + 1];
		StringTokenizer st;

		for(int i = 1; i <= n * n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			order[i] = idx;
			for(int j = 1; j < 5; j++) {
				list[idx][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 1; i <= n * n; i++) {
			placeStudent(order[i]);
		}

		System.out.println(calTotalScore());
	}

	static void placeStudent(int student) {
		int maxLike = -1;
		int maxEmpty = -1;
		int bestX = -1;
		int bestY = -1;

		for(int x = 1; x <= n; x++) {
			for(int y = 1; y <= n; y++) {
				if(board[x][y] != 0) continue;

				int likeCount = countFriends(x, y, student);
				int emptyCount = countEmpty(x, y);

				if(likeCount > maxLike) {
					maxLike = likeCount;
					maxEmpty = emptyCount;
					bestX = x;
					bestY = y;
				} else if (likeCount == maxLike && emptyCount > maxEmpty) {
					maxEmpty = emptyCount;
					bestX = x;
					bestY = y;
				}
			}
		}

		board[bestX][bestY] = student;
	}

	static int countFriends(int x, int y, int student) {
		int count = 0;
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(nx >= 1 && ny >=1 && nx <= n && ny <= n) {
				for(int j = 1; j <= 4; j++) {
					if(board[nx][ny] == list[student][j]) {
						count++;
						break;
					}
				}
			}
		}

		return count;
	}

	static int countEmpty(int x, int y) {
		int count = 0;
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
				if(board[nx][ny] == 0) {
					count++;
				}
			}
		}
		return count;
	}

	static int calTotalScore() {
		int totalScore = 0;

		for(int x = 1; x <= n; x++) {
			for(int y = 1; y <= n; y++) {
				int student = board[x][y];
				int likeCount = countFriends(x, y, student);

				if(likeCount == 1) totalScore += 1;
				else if(likeCount == 2) totalScore += 10;
				else if(likeCount == 3) totalScore += 100;
				else if(likeCount == 4) totalScore += 1000;
			}
		}
		return totalScore;
	}
}