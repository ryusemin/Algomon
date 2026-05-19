import java.util.*;
import java.io.*;

public class Main {
    static int n, m;

    static List<Integer>[] edges;
    static List<Integer>[] reverse;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n + 1];
        reverse = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            edges[x].add(y);
            reverse[y].add(x);
        }

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        List<Integer> going = intersect(
                reachable(s, t, edges),
                reachable(t, -1, reverse)
        );

        List<Integer> coming = intersect(
                reachable(t, s, edges),
                reachable(s, -1, reverse)
        );

        // 둘 다 만족하는 정점
        List<Integer> candidates = intersect(going, coming);

        int answer = 0;

        for(int x : candidates){
            if(x == s || x == t) continue;
            answer++;
        }

        System.out.println(answer);
    }

    static List<Integer> reachable(int start, int ignore, List<Integer>[] graph){
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        boolean[] visited = new boolean[n + 1];

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int now = q.poll();
            result.add(now);
            if(now == ignore) continue;

            for(int next : graph[now]){
                if(visited[next]) continue;

                visited[next] = true;
                q.offer(next);
            }
        }
        return result;
    }

    static List<Integer> intersect(List<Integer> a, List<Integer> b){
        List<Integer> result = new ArrayList<>();

        Collections.sort(a);
        Collections.sort(b);

        int pa = 0;
        int pb = 0;

        while(pa < a.size() && pb < b.size()){
            int av = a.get(pa);
            int bv = b.get(pb);

            if(av == bv){
                result.add(av);
                pa++;
                pb++;
            }
            else if(av < bv) pa++;
            else pb++;
        }

        return result;
    }
}
