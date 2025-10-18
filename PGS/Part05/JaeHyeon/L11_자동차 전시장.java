import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, k; // n: 노드 수, m: 간선 수, k: 사람 수
    static List<Integer>[] list;
    static int[][] cost;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            list[src].add(dst);
        }
        
        cost = new int[k][n + 1];
        for (int i = 0; i < k; i++) {
            Arrays.fill(cost[i], -1);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int start = Integer.parseInt(st.nextToken());

            int[] dist = new int[n + 1];
            Arrays.fill(dist, -1);

            Queue<Integer> q = new ArrayDeque<>();
            q.add(start);
            dist[start] = 0;

            while(!q.isEmpty()) {
                int now = q.poll();

                for (int next : list[now]) {
                    if (dist[next] == -1) {
                        q.add(next);
                        dist[next] = dist[now] + 1;
                    }
                }
            }

            for (int j = 1; j <= n; j++) {
                cost[i][j] = dist[j];
            }
        }

        for (int i = 1; i <= n; i++) {
            boolean canGo = true;
            int max = 0;
            for (int j = 0; j < k; j++) {
                if (cost[j][i] == -1) {
                    canGo = false;
                    break;
                }
                max = Math.max(max, cost[j][i]);
            }

            if (canGo) {
                answer = Math.min(answer, max);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}