import java.util.*;

class Solution {
    static boolean[] visited;
    static List<String> answer;
    static String[][] ticketss;
    
    public String[] solution(String[][] tickets) {
        ticketss = tickets;
        answer = new ArrayList<>();
        visited = new boolean[tickets.length];
        
        dfs("ICN", "ICN",  0);
        
        Collections.sort(answer);
        
        return answer.get(0).split(" ");
    }
    
    void dfs(String s,String s2, int cnt){
        if(cnt == ticketss.length){
            answer.add(s2);
            return; 
        }
        
        for(int i=0; i< ticketss.length; i++){
            if(s.equals(ticketss[i][0]) && !visited[i]){
                visited[i] = true;
                dfs(ticketss[i][1], s2+ " " +ticketss[i][1], cnt + 1);
                visited[i]= false;
            }
        }
        
    }
    
}