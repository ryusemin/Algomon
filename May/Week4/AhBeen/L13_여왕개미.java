import java.util.*;
import java.io.*;

public class Main {
	static int cmds;
	static ArrayList<Boolean> isDestroy = new ArrayList<>(Arrays.asList(false));
	static ArrayList<Integer> xList = new ArrayList<>(Arrays.asList(0));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int Q = Integer.parseInt(br.readLine());

		StringTokenizer st;

		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op == 100) {
				int numHome = Integer.parseInt(st.nextToken());

				for(int j = 1; j <= numHome; j++) {
					int x = Integer.parseInt(st.nextToken());
					buildHome(x);
				}
			} else if(op == 200) {
				int x = Integer.parseInt(st.nextToken());
				buildHome(x);
			} else if (op == 300) {
				int idx = Integer.parseInt(st.nextToken());
				destroyHome(idx);
			} else if (op == 400) {
				int ants = Integer.parseInt(st.nextToken());
				inspection(ants);
			}
		}
	}

	static void buildHome(int p) {
		xList.add(p);
		isDestroy.add(false);
	}

	static void destroyHome(int q) {
		isDestroy.set(q, true);
	}

	static void inspection(int num) {
		int low = 0;
		int high = 1000000000;

		int min = 0;

		while(low <= high) {
			int mid = (low + high) / 2;

			//mid 내 하나의 개미가 커버 가능한 영역으로 필요로 하는 구간의 수
			int intervalsNeeded = 0;
			int lastCovered = -1000000000;

			for(int i = 1; i < xList.size(); i++) {
				if(isDestroy.get(i)) {
					continue;
				}

				int curX = xList.get(i);

				if(curX - lastCovered > mid) {
					lastCovered = curX;
					intervalsNeeded++;
				}
			}

			if(intervalsNeeded <= num) {
				min = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(min);
	}
}