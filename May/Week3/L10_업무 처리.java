import java.util.*;
import java.io.*;

public class Main {
	static Queue<Integer>[][] workers;
	static int totalWork = 0;
	static int H, K, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int numNodes = (int) Math.pow(2, H + 1);

		workers = new ArrayDeque[numNodes][2];

		for(int i = 0; i < numNodes; i++) {
			workers[i][0] = new ArrayDeque<>();
			workers[i][1] = new ArrayDeque<>();
		}

		int leftStart = (int) Math.pow(2, H);
		for(int i = leftStart; i < numNodes; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < K; j++) {
				workers[i][0].add(Integer.parseInt(st.nextToken()));
			}
		}

		for(int i = 1; i <= R; i++) {
			processWork(i);
		}

		System.out.println(totalWork);
	}

	static void processWork(int day) {
		int leafStart = (int) Math.pow(2, H);
		int numNodes = (int) Math.pow(2, H + 1);

		int rootIdx = (day % 2 == 0) ? 1 : 0;
		if (!workers[1][rootIdx].isEmpty()) {
			totalWork += workers[1][rootIdx].poll();
		}

		for (int i = 2; i < leafStart; i++) {
			int workIdx = (day % 2 == 0) ? 1 : 0;
			if (!workers[i][workIdx].isEmpty()) {
				int work = workers[i][workIdx].poll();
				int parent = i / 2;
				int side = (i % 2 == 0) ? 0 : 1;
				workers[parent][side].add(work);
			}
		}

		for (int i = leafStart; i < numNodes; i++) {
			if (!workers[i][0].isEmpty()) {
				int work = workers[i][0].poll();
				int parent = i / 2;
				int side = (i % 2 == 0) ? 0 : 1;
				workers[parent][side].add(work);
			}
		}
	}
}