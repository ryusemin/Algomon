import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        List<Node>[] graph = null;
        int[] distance = null;
        int start = 0;

        Map<Integer, int[]> alive = new HashMap<>();
        
        PriorityQueue<Manage> pq = new PriorityQueue<>((m1, m2) -> {
            long p1 = (m1.dist == Integer.MAX_VALUE ? Long.MIN_VALUE : (long)m1.revenue - m1.dist);
            long p2 = (m2.dist == Integer.MAX_VALUE ? Long.MIN_VALUE : (long)m2.revenue - m2.dist);
            if (p1 == p2) {
                return m1.id - m2.id;
            }
            return Long.compare(p2, p1);
        });


        StringTokenizer st;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch(cmd) {
                case 100:
                    int n = Integer.parseInt(st.nextToken());
                    int m = Integer.parseInt(st.nextToken());

                    graph = new ArrayList[n];
                    for (int j = 0; j < n; j++) {
                        graph[j] = new ArrayList<>();
                    }

                    for (int j = 0; j < m; j++) {
                        int u = Integer.parseInt(st.nextToken());
                        int v = Integer.parseInt(st.nextToken());
                        int w = Integer.parseInt(st.nextToken());
                        graph[u].add(new Node(v, w));
                        graph[v].add(new Node(u, w));
                    }

                    distance = dijkstra(start, graph);
                    break;

                case 200:
                    int newId = Integer.parseInt(st.nextToken());
                    int revenue = Integer.parseInt(st.nextToken());
                    int dest = Integer.parseInt(st.nextToken());

                    alive.put(newId, new int[]{revenue, dest});

                    pq.add(new Manage(newId, revenue, dest, distance[dest]));
                    break;

                case 300:
                    int removeId = Integer.parseInt(st.nextToken());
                    alive.remove(removeId);
                    break;

                case 400:
                    int ans = -1;

                    while (!pq.isEmpty()) {
                        Manage top = pq.poll();

                        int[] cur = alive.get(top.id);
                        if (cur == null) continue;

                        if (cur[0] != top.revenue || cur[1] != top.dest)    continue;

                        long profit = (top.dist == Integer.MAX_VALUE ? Long.MIN_VALUE : (long)top.revenue - top.dist);

                        if (profit < 0) {
                            ans = -1;
                            break;
                        } else {
                            ans = top.id;
                            alive.remove(top.id);
                            break;
                        }
                    }

                    sb.append(ans).append("\n");
                    break;

                case 500:
                    int s = Integer.parseInt(st.nextToken());
                    start = s;
                    distance = dijkstra(start, graph);

                    PriorityQueue<Manage> newpq2 = new PriorityQueue<>((m1, m2) -> {
                        long p1 = (m1.dist == Integer.MAX_VALUE ? Long.MIN_VALUE : (long)m1.revenue - m1.dist);
                        long p2 = (m2.dist == Integer.MAX_VALUE ? Long.MIN_VALUE : (long)m2.revenue - m2.dist);
                        if (p1 == p2) {
                            return m1.id - m2.id;
                        }
                        return Long.compare(p2, p1);
                    });

                    for (Map.Entry<Integer, int[]> e : alive.entrySet()) {
                        int id = e.getKey();
                        int[] info = e.getValue();
                        int newDist = distance[info[1]];
                        newpq2.add(new Manage(id, info[0], info[1], newDist));
                    }
                    
                    pq = newpq2;
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    static int[] dijkstra(int start, List<Node>[] graph) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.w - n2.w;
        });
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.w > dist[now.u])    continue;

            for (Node next : graph[now.u]) {
                int newDist = now.w + next.w;

                if (newDist < dist[next.u]) {
                    dist[next.u] = newDist;
                    pq.add(new Node(next.u, newDist));
                }
            }
        }
        return dist;
    }
}

class Node {
    int u, w;

    Node (int u, int w) {
        this.u = u;
        this.w = w;
    }
}

class Manage {
    int id, revenue, dest, dist;
    Manage(int id, int revenue, int dest, int dist) {
        this.id = id;
        this.revenue = revenue;
        this.dest = dest;
        this.dist = dist;
    }
}