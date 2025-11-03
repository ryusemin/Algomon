import java.util.*;
import java.io.*;

public class Main {
	static class Point {
		int x;
		int y;
		int color;
		Point(int x, int y, int color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Point[] points = new Point[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y, c);
		}

		Arrays.sort(points, Comparator.comparingInt(p -> p.x));

		int result = Integer.MAX_VALUE;
		// 왼쪽 포인터 이동
		for(int i = 0; i < N; i++) {
			List<Point> active = new ArrayList<>();
			//오른쪽 포인터 이동
			for(int j = i; j < N; j++) {
				active.add(points[j]);
				active.sort(Comparator.comparingInt(p -> p.y));

				int[] colorCount = new int[K + 1];
				int haveColors = 0;
				int lY = 0;

				for(int rY = 0; rY < active.size(); rY++) {
					Point pr = active.get(rY);
					if(colorCount[pr.color] == 0) haveColors++;
					colorCount[pr.color]++;

					//모든 색 포함 시
					while(haveColors == K) {
						int xL = points[i].x;
						int xH = points[j].x;
						int yL = active.get(lY).y;
						int yH = active.get(rY).y;

						int area = (xH - xL) * (yH - yL);
						result = Math.min(area, result);

						Point pl = active.get(lY);
						colorCount[pl.color]--;
						if(colorCount[pl.color] == 0) haveColors--;
						lY++;
					}
				}
			}
		}
		if (result == Integer.MAX_VALUE) result = 0;
		System.out.println(result);
	}
}