class Solution {
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visited = new boolean[dungeons.length];
        
        for(int i=0; i < dungeons.length; i++){
            visited[i] = true;
            answer = Math.max(answer, dfs(i, k, dungeons , 0));
            visited[i] = false;
        }
        
        return answer;
    }
    static int dfs(int i, int k, int[][] dungeons , int count ){
        if(k < dungeons[i][0]) return count;
        k -= dungeons[i][1];
        
        int now = count+1;
        
        for(int j=0; j < dungeons.length; j++){
            if(!visited[j]){
                visited[j] = true;
                now = Math.max(now, dfs(j, k, dungeons, count+1));
                visited[j] = false;
            }
        }
        return now;
        
    }
}