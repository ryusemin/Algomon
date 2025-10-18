class Solution {
    static int answer = 0;
    public int solution(int k, int[][] dungeons) {
    
        dfs(k, dungeons, new boolean[dungeons.length], 0);
        return answer;
    }
    
    void dfs(int k, int[][] dungeons, boolean[] visited, int cnt) {
        
        boolean canExplorer = false;
        
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (dungeons[i][0] <= k) {
                    canExplorer = true;
                    break;
                }            
            }
        }
        
        if (!canExplorer) {
            answer = Math.max(answer, cnt);
            return;
        }
        
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (dungeons[i][0] <= k) {
                    visited[i] = true;
                    dfs(k - dungeons[i][1], dungeons, visited, cnt + 1);
                    visited[i] = false;
                }
            }
        }
    } 
}

/*
최소 필요 피로도, 소모 피로도

*/