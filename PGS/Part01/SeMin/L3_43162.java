import java.util.*;

class Solution {
    static int answer;
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        answer = 0;
        visited = new boolean[n];

        for(int i = 0; i< n; i++){
            if(visited[i]) continue;
            bfs(n, computers, i);
            answer++;
        }

        return answer;
    }

    public void bfs(int n, int[][] computers, int num){
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);
        visited[num] = true;
        while(!q.isEmpty()){
            int a = q.poll();
            for(int i = 0; i< n; i++){
                if(computers[a][i] == 1 && !visited[i]){
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

}