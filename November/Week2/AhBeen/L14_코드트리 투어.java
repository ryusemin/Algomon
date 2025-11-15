import java.util.*;
import java.io.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static final int MAX_N = 2000;
	static final int MAX_ID = 30002;

	static int N;
	static int M;

	static int[] D;
	static boolean[] isMade;
	static boolean[] isCanceled;
	static int S;
	static List<int[]>[] A;
	static class Node {
		int id;
		int revenue;
		int city;
		long profit;

		Node(int id, int revenue, int city, long profit) {
			this.id = id;
			this.revenue = revenue;
			this.city = city;
			this.profit = profit;
		}
	}

	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int Q = Integer.parseInt(br.readLine());

		isMade = new boolean[MAX_ID];
		isCanceled = new boolean[MAX_ID];

		pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				if (n1.profit == n2.profit) {
					return Integer.compare(n1.id, n2.id);
				}
				return Long.compare(n2.profit, n1.profit);
			}
		});

		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());

			if(T == 100) {
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				int[] arr = new int[m * 3];
				for(int j = 0; j < m * 3; j++) {
					arr[j] = Integer.parseInt(st.nextToken());
				}
				build(n, m, arr);
				S = 0;
				dijkstra();
			} else if(T == 200) {
				int id = Integer.parseInt(st.nextToken());
				int revenue = Integer.parseInt(st.nextToken());
				int city = Integer.parseInt(st.nextToken());
				addNode(id, revenue, city);
			} else if(T == 300) {
				int id = Integer.parseInt(st.nextToken());
				cancel(id);
			} else if(T == 400) {
				System.out.println(sell());
			} else if(T == 500) {
				int s = Integer.parseInt(st.nextToken());
				change(s);
			}
		}
	}

	static void dijkstra() {
		D = new int[N];
		Arrays.fill(D, INF);

		PriorityQueue<int[]> pq2 = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		D[S] = 0;
		pq2.offer(new int[]{S, 0});

		while (!pq2.isEmpty()) {
			int[] cur = pq2.poll();
			int now = cur[0];
			int dist = cur[1];

			if (dist > D[now]) continue;

			for (int[] edge : A[now]) {
				int next = edge[0];
				int w = edge[1];
				int nd = dist + w;

				if (nd < D[next]) {
					D[next] = nd;
					pq2.offer(new int[]{next, nd});
				}
			}
		}
	}

	static void build(int n, int m, int[] arr) {
		N = n;
		M = m;
		A = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			A[i] = new ArrayList<>();
		}
		//주어진 M개 간선으로 인접 리스트 만들기
		for(int i = 0; i < M; i++) {
			int u = arr[i * 3];
			int v = arr[i * 3 + 1];
			int w = arr[i * 3 + 2];
			A[u].add(new int[]{v, w});
			A[v].add(new int[]{u, w});
		}

		pq.clear();
		Arrays.fill(isMade, false);
		Arrays.fill(isCanceled, false);
	}

	static void addNode(int id, int revenue, int city) {
		isMade[id] = true;
		//도착지까지 도달이 불가능한 경우 profit을 음수로 설정하여 판매 불가 처리
		long profit = (D[city] == INF) ? Long.MIN_VALUE : (long) revenue - D[city];
		pq.offer(new Node(id, revenue, city, profit));
	}

	static void cancel(int id) {
		if(isMade[id]) {
			isCanceled[id] = true;
		}
	}

	static int sell() {
		while(!pq.isEmpty()) {
			Node n = pq.peek();

			if (n.profit < 0) return -1;

			if(n.profit < 0) {
				break;
			}

			pq.poll();

			if(!isCanceled[n.id]) {
				isCanceled[n.id] = true;
				return n.id;
			}
		}
		return -1;
	}

	static void change(int s) {
		S = s;
		dijkstra();

		ArrayList<Node> tempNode = new ArrayList<>();

		while(!pq.isEmpty()) {
			tempNode.add(pq.poll());
		}

		for(Node n : tempNode) {
			n.profit = (D[n.city] == INF) ? Long.MIN_VALUE : (long)n.revenue - D[n.city];
			pq.offer(n);
		}
	}
}