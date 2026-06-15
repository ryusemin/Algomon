import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int T;
	static Student[][] students;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Student {
		int r, c;
		int food;          // 비트마스킹: 1(T), 2(C), 4(M)
		int faith;
		boolean isLeader;
		boolean canAttack;

		Student(int r, int c, char foodChar) {
			this.r = r;
			this.c = c;
			if (foodChar == 'T') this.food = 1;
			else if (foodChar == 'C') this.food = 2;
			else if (foodChar == 'M') this.food = 4;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		students = new Student[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				students[i][j] = new Student(i, j, line.charAt(j));
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				students[i][j].faith = Integer.parseInt(st.nextToken());
			}
		}

		for (int t = 0; t < T; t++) {
			early();
			List<Student> leaders = mid();
			late(leaders);

			long[] sums = new long[8];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sums[students[i][j].food] += students[i][j].faith;
				}
			}

			System.out.println(
				sums[7] + " " + sums[3] + " " + sums[5] + " " +
					sums[6] + " " + sums[4] + " " + sums[2] + " " + sums[1]
			);
		}
	}

	static void early() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				students[i][j].faith += 1;
				students[i][j].isLeader = false;
				students[i][j].canAttack = false;
			}
		}
	}

	static List<Student> mid() {
		boolean[][] visited = new boolean[N][N];
		List<Student> leaders = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					List<Student> group = new ArrayList<>();
					Queue<Student> q = new LinkedList<>();

					q.add(students[i][j]);
					visited[i][j] = true;
					group.add(students[i][j]);

					int targetFood = students[i][j].food;

					while (!q.isEmpty()) {
						Student cur = q.poll();

						for (int d = 0; d < 4; d++) {
							int nx = cur.r + dx[d];
							int ny = cur.c + dy[d];

							if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
								if (students[nx][ny].food == targetFood) {
									visited[nx][ny] = true;
									q.add(students[nx][ny]);
									group.add(students[nx][ny]);
								}
							}
						}
					}

					Student best = group.get(0);
					for (int k = 1; k < group.size(); k++) {
						Student s = group.get(k);
						if (s.faith > best.faith) {
							best = s;
						} else if (s.faith == best.faith) {
							if (s.r < best.r) best = s;
							else if (s.r == best.r && s.c < best.c) best = s;
						}
					}

					best.faith += (group.size() - 1);
					for (Student s : group) {
						if (s != best) s.faith -= 1;
					}

					best.isLeader = true;
					best.canAttack = true;
					leaders.add(best);
				}
			}
		}
		return leaders;
	}

	static void late(List<Student> leaders) {
		Collections.sort(leaders, (a, b) -> {
			int countA = Integer.bitCount(a.food);
			int countB = Integer.bitCount(b.food);

			if (countA != countB) return Integer.compare(countA, countB);
			if (a.faith != b.faith) return Integer.compare(b.faith, a.faith);
			if (a.r != b.r) return Integer.compare(a.r, b.r);
			return Integer.compare(a.c, b.c);
		});

		for (Student leader : leaders) {
			if (!leader.canAttack) continue;

			int B = leader.faith;
			if (B <= 1) continue;

			int dir = B % 4;
			int x = B - 1;

			leader.faith = 1;

			int nr = leader.r;
			int nc = leader.c;

			while (x > 0) {
				nr += dx[dir];
				nc += dy[dir];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;

				Student target = students[nr][nc];

				if (target.food == leader.food) continue;

				int y = target.faith;

				if (x > y) {
					target.food = leader.food;
					target.faith += 1;
					x -= (y + 1);

					if (target.isLeader) target.canAttack = false;

				} else {
					target.food |= leader.food;
					target.faith += x;
					x = 0;

					if (target.isLeader) target.canAttack = false;
				}
			}
		}
	}
}