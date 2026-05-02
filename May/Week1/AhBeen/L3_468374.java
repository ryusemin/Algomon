import java.util.*;

class Solution {
	int N;
	int M;
	int[][] board;
	int[] dr = {0, 0, 1, 0, -1};
	int[] dc = {0, 1, 0, -1, 0};
	class Cell {
		int r, c, id;
		Cell(int r, int c, int id) {
			this.r = r;
			this.c = c;
			this.id = id;
		}
	}
	public int[][] solution(int[][] board, int[][] commands) {
		this.N = board.length;
		this.M = board[0].length;
		this.board = new int[N][M];

		for(int i = 0; i < N; i++) {
			this.board[i] = board[i].clone();
		}

		for(int[] cmd : commands) {
			process(cmd[0], cmd[1]);
		}

		return this.board;
	}

	void process(int startApp, int dir) {
		Set<Integer> group = getGroup(startApp, dir);
		moveGroup(group, dir);

		while(true) {
			List<Integer> broken = findBrokenApps(dir);
			if(broken.isEmpty()) break;

			int brokenApp = broken.get(0);
			Set<Integer> newGroup = getGroup(brokenApp, dir);
			moveGroup(newGroup, dir);
		}
	}

	Set<Integer> getGroup(int startApp, int dir) {
		Set<Integer> group = new HashSet<>();
		Queue<Integer> q = new ArrayDeque<>();

		group.add(startApp);
		q.offer(startApp);

		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(board[r][c] != cur) continue;

					int nr = (r + dr[dir] + N) % N;
					int nc = (c + dc[dir] + M) % M;

					int nx = board[nr][nc];
					if(nx != 0 && !group.contains(nx)) {
						group.add(nx);
						q.offer(nx);
					}
				}
			}
		}
		return group;
	}

	void moveGroup(Set<Integer> group, int dir) {
		List<Cell> cells = new ArrayList<>();

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(group.contains(board[r][c])) {
					cells.add(new Cell(r, c, board[r][c]));
				}
			}
		}

		for(Cell cell : cells) {
			board[cell.r][cell.c] = 0;
		}

		for(Cell cell : cells) {
			int nr = (cell.r + dr[dir] + N) % N;
			int nc = (cell.c + dc[dir] + M) % M;
			board[nr][nc] = cell.id;
		}
	}

	List<Integer> findBrokenApps(int dir) {
		List<Integer> broken = new ArrayList<>();
		boolean[] added = new boolean[101];

		if(dir == 1 || dir == 3) {
			for (int r = 0; r < N; r++) {
				int leftId = board[r][0];
				int rightId = board[r][M - 1];

				if(leftId == 0 || leftId != rightId) continue;

				boolean brokenInThisRow = false;
				for(int c = 0; c < M; c++) {
					if(board[r][c] != leftId) {
						brokenInThisRow = true;
						break;
					}
				}

				if(brokenInThisRow && !added[leftId]) {
					broken.add(leftId);
					added[leftId] = true;
				}
			}
		} else {
			for(int c = 0; c < M; c++) {
				int topId = board[0][c];
				int bottomId = board[N - 1][c];

				if(topId == 0 || topId != bottomId) continue;

				boolean brokenInThisCol = false;
				for(int r = 0; r < N; r++) {
					if(board[r][c] != topId) {
						brokenInThisCol = true;
						break;
					}
				}
				if(brokenInThisCol && !added[topId]) {
					broken.add(topId);
					added[topId] = true;
				}
			}
		}
		return broken;
	}
}