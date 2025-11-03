import java.util.*;
import java.io.*;
public class Main {
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	static int count = 0;

	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] selected = new int[7]; //7자리를 조합으로 선택을 위한 배열
		char [][] map = new char[5][5];
		for(int i = 0; i < 5; i++) {
			String line = br.readLine();
			for(int j = 0; j < 5; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		comb(map, selected, 0, 0);
		System.out.println(count);
	}

	//25칸 중에서 7칸을 선택
	//7칸을 사용해서 bfs를 통해 연결되어 있는지랑 S의 개수를 확인
	static void comb(char[][] map, int[] selected, int start, int depth) {
		if(depth == 7) {
			if(bfs(map, selected)) count++;
			return;
		}
		//조합
		for(int i = start; i < 25; i++) {
			selected[depth] = i;
			comb(map, selected, i + 1, depth + 1);
		}
	}

	//7개칸이 모두 연결 여부 확인
	//S가 4개 이상인지 확인
	static boolean bfs(char[][] map, int[] selected) {
		int count_s = 0;
		boolean[][] visited = new boolean[5][5];
		Queue<Node> q = new ArrayDeque<>();

		boolean[][] choose = new boolean[5][5]; //선택된 칸 표시 배열

		//선택 칸 표시, S 개수가 몇개인지
		for(int idx: selected) {
			int x = idx / 5;
			int y = idx % 5;
			choose[x][y] = true;
			if(map[x][y] == 'S') count_s++;
		}
		if(count_s < 4) return false;

		int startX = selected[0] / 5;
		int startY = selected[0] % 5;
		q.add(new Node(startX, startY));
		visited[startX][startY] = true;
		int connected = 1;	//연결 칸 개수 몇개인지 세는 변수

		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
				if(!choose[nx][ny] || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				q.add(new Node(nx, ny));
				connected++;
			}
		}
		// 7칸 모두 연결되어있다면 true;
		return connected == 7;
	}
}
