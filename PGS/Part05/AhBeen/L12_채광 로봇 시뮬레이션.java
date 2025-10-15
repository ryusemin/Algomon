import java.util.Scanner;

public class Main {
	// 격자의 최대 크기. N의 최대 크기는 1000, 1-based index(1부터 시작) 사용 및 안전을 위함.
	static final int MAX_N = 1005;
	static final int MIN_VALUE = -1000000000;

	static int n, t;
	static int[][] arr = new int[MAX_N][MAX_N];

	static int[][] mx = new int[MAX_N][MAX_N];

	// dp[i][j][k]
	// k=0: 시간역행 쓰지 않았을때
	// k=1: 시간역행 썻을 때
	static int[][][] dp = new int[MAX_N][MAX_N][2];

	//최대 이익을 찾는 재귀 함수
	static void calculate(int i, int j, int x, int y, int passTime, int profit) {
		// i, j: 경로의 시작점 (mx 배열에 값을 저장할 인덱스)
		// x, y: 현재 로봇의 위치
		// passedTime: 현재까지 경과한 시간
		// profit: 현재까지 누적된 이익

		//T초가 경과하면 탐색을 멈춥니다.
		if (passedTime == t) {
			// 시작점 (i, j)에 대해 현재 경로의 이익을 최대값으로 갱신합니다.
			mx[i][j] = Math.max(mx[i][j], profit);
			return;
		}

		//오른쪽으로 이동 (x+1, y)
		if (x + 1 <= n) {
			// 시간을 1초 증가, 이익에 다음 칸 arr[x+1][y]를 더해 재귀 호출
			calculate(i, j, x + 1, y, passedTime + 1, profit + arr[x + 1][y]);
		}

		//아래쪽으로 이동 (x, y+1)
		if (y + 1 <= n) {
			// 시간을 1초 증가, 이익에 다음 칸 arr[x][y+1]를 더해 재귀 호출
			calculate(i, j, x, y + 1, passedTime + 1, profit + arr[x][y + 1]);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N, T 및 격자 데이터 입력
		n = sc.nextInt();
		t = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				mx[i][j] = MIN_VALUE;
				dp[i][j][0] = dp[i][j][1] = MIN_VALUE;
			}
		}

		// 모든 칸 (i, j)를 시작점으로 하여 T초 동안 얻는 최대 이익 max[i][j]를 계산합니다.
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// (i, j)에서 시작, 초기 이익은 시작점의 값 a[i][j], 경과 시간은 0
				calculate(i, j, i, j, 0, arr[i][j]);
			}
		}

		// 시작점 (1, 1)의 이익을 설정합니다. (장치는 아직 사용하지 않음)
		dp[1][1][0] = arr[1][1];

		// (1, 1)부터 (N, N)까지 순회하며 DP 상태를 갱신합니다.
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {

				// 장치 사용 직전 상태 (dp[i][j][0])가 도달 가능하고, T초 경로(max[i][j])가 있다면
				if (dp[i][j][0] != MIN_VALUE && max[i][j] != MIN_VALUE) {
					// (i, j)에서 장치를 '처음' 사용하여 T초 이동했을 때의 총 이익을 계산합니다.
					// dp[i][j][0] (직전 이익) + mx[i][j] (T초 재탐색 이익)
					dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j][0] + max[i][j]);
				}


				// 아래로: (i+1, j)로 이동
				if (i + 1 <= n) {
					// k=0 (미사용 상태) 전이: 이전 칸 (i, j)의 미사용 이익 + 다음 칸 a[i+1][j]
					dp[i + 1][j][0] = Math.max(dp[i + 1][j][0], dp[i][j][0] + arr[i + 1][j]);

					// k=1 (사용 상태) 전이: 이전 칸 (i, j)의 사용 이익 + 다음 칸 a[i+1][j]
					dp[i + 1][j][1] = Math.max(dp[i + 1][j][1], dp[i][j][1] + arr[i + 1][j]);
				}

				// 오른쪽: (i, j+1)로 이동
				if (j + 1 <= n) {
					// k=0 (미사용 상태) 전이
					dp[i][j + 1][0] = Math.max(dp[i][j + 1][0], dp[i][j][0] + arr[i][j + 1]);
					// k=1 (사용 상태) 전이
					dp[i][j + 1][1] = Math.max(dp[i][j + 1][1], dp[i][j][1] + arr[i][j + 1]);
				}
			}
		}
		// 최종 도착점 (N, N)에서의 두 상태 (미사용/사용) 중 더 큰 최대 이익을 출력합니다.
		System.out.println(Math.max(dp[n][n][0], dp[n][n][1]));

		sc.close();
	}
}