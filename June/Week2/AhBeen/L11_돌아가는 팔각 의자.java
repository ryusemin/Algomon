import java.util.*;
import java.io.*;

public class Main {
	static class Chair {
		int idx;
		int dir;

		Chair(int idx, int dir) {
			this.idx = idx;
			this.dir = dir;
		}
	}

	static List<Chair> rotateInfo;
	static int[][] chairs = new int[5][8];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		for(int i = 1; i <= 4; i++) {
			String line = br.readLine();
			for(int j = 0; j < 8; j++) {
				chairs[i][j] = line.charAt(j) - '0';
			}
		}

		int k = Integer.parseInt(br.readLine());

		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			rotateInfo = new ArrayList<>();
			rotateInfo.add(new Chair(n, d));

			searchL(n, d);
			searchR(n, d);

			for(Chair c : rotateInfo) {
				if(c.dir == 1) {
					rotateR(c);
				} else {
					rotateL(c);
				}
			}
		}

		int sum = 0;

		for(int i = 1; i < 5; i++) {
			sum += (int) Math.pow(2, i - 1) * chairs[i][0];
		}

		System.out.println(sum);
	}

	static void searchL(int n, int d) {
		int curD = d;
		for(int i = n; i > 1; i--) {
			curD = curD * -1;
			if(chairs[i][6] != chairs[i - 1][2]) {
				rotateInfo.add(new Chair(i - 1, curD));
			} else {
				break;
			}
		}
	}

	static void searchR(int n, int d) {
		int curD = d;
		for(int i = n; i < 4; i++) {
			curD = curD * -1;
			if(chairs[i][2] != chairs[i + 1][6]) {
				rotateInfo.add(new Chair(i + 1, curD));
			} else {
				break;
			}
		}
	}

	static void rotateR(Chair c) {
		int idx = c.idx;

		int[] chair = chairs[idx];

		int tmp = chair[7];

		for(int i = 7; i >= 1; i--) {
			chair[i] = chair[i - 1];
		}

		chair[0] = tmp;

		chairs[idx] = chair;
	}

	static void rotateL(Chair c) {
		int idx = c.idx;

		int[] chair = chairs[idx];

		int tmp = chair[0];

		for(int i = 0; i < 7; i++) {
			chair[i] = chair[i + 1];
		}

		chair[7] = tmp;

		chairs[idx] = chair;
	}
}