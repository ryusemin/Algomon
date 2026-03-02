import java.util.*;
import java.io.*;

class Main {
	static final int MAX_SIZE = 8;
	static final int KING = 0;
	static final int STONE = 1;

	static Map<String, Pos> direction = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Pos[] poses = new Pos[2];
		for (int i = 0; i < 2; i++) {
			char[] input = st.nextToken().toCharArray();
			poses[i] = new Pos(input[0] - 'A' + 1, input[1] - '0');
		}

		initializeDirection();

		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String dir = br.readLine();
			int dx = direction.get(dir).x;
			int dy = direction.get(dir).y;

			int nx = poses[KING].x + dx;
			int ny = poses[KING].y + dy;

			if (!(1 <= nx && nx <= MAX_SIZE) || !(1 <= ny && ny <= MAX_SIZE)) continue;


			if (nx == poses[STONE].x && ny == poses[STONE].y) {
				int snx = poses[STONE].x + dx;
				int sny = poses[STONE].y + dy;

				if (!(1 <= snx && snx <= MAX_SIZE) || !(1 <= sny && sny <= MAX_SIZE)) continue;

				poses[STONE].x = snx;
				poses[STONE].y = sny;
			}
			poses[KING].x = nx;
			poses[KING].y = ny;
		}

		System.out.println(poses[KING]);
		System.out.println(poses[STONE]);

	}

	static void initializeDirection() {
		direction.put("R", new Pos( 1, 0 ));
		direction.put("L", new Pos( -1, 0 ));
		direction.put("B", new Pos( 0, -1 ));
		direction.put("T", new Pos( 0, 1 ));
		direction.put("RT", new Pos( 1, 1 ));
		direction.put("LT", new Pos( -1, 1 ));
		direction.put("RB", new Pos( 1, -1 ));
		direction.put("LB", new Pos( -1, -1 ));
	}

	static class Pos {
		int x, y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("%c%d", x + 'A' - 1, y);
		}
	}
}