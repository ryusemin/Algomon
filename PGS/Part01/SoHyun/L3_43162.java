import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] v = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                dfs(i, computers, v);
                answer++;
            }
        }

        return answer;
    }

    public void dfs(int node, int[][] computers, boolean[] v) {
        v[node] = true;

        for (int i = 0; i < computers[node].length; i++) {
            if (computers[node][i] == 1 && !v[i]) {
                dfs(i, computers, v);
            }
        }
    }
}