import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        
        List<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (computers[i][j] == 1) {
                    list[i].add(j);
                    list[j].add(i);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                q.add(i);
                visited[i] = true;
                
                while(!q.isEmpty()) {
                    int now = q.poll();
                    
                    for (int next : list[now]) {
                        if (!visited[next]) {
                            q.add(next);
                            visited[next] = true;
                        }
                    }
                }                
            }
        }
        
        return answer;
    }
}