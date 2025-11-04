import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		int x;
		int y;
		int dist;
		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		//시작 위치
		int x = 0;
		int y = 0;
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					x = i;
					y = j;
					map[i][j] = 0;
				}
			}
		}

		int size = 2;
		int count = 0;
		int t = 0;

		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		while(true) {
			//가장 먼저 먹을 물고기 탐색
			Node target = bfs(N, map, x, y, size, dx, dy);
			//먹을 물고기가 없으면 종료
			if(target == null) break;
			t += target.dist;
			x = target.x;
			y = target.y;
			map[x][y] = 0;
			count++;
			if(count == size) {
				size++;
				count = 0;
			}
		}
		System.out.println(t);
	}

	static Node bfs(int N, int[][] map, int x, int y, int size, int[] dx, int[] dy) {
		boolean[][] visited = new boolean[N][N];
		//조건에 맞게 우선순위 정렬
		PriorityQueue<Node> pq = new PriorityQueue<>(
			Comparator.comparingInt((Node n) -> n.dist)
				.thenComparingInt(n -> n.x)
				.thenComparingInt(n -> n.y)
		);

		pq.add(new Node(x, y, 0));
		visited[x][y] = true;

		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < size) {
				return cur;
			}
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny] || map[nx][ny] > size) continue;
				visited[nx][ny] = true;
				pq.add(new Node(nx, ny, cur.dist + 1));
			}
		}
		return null;
	}
}