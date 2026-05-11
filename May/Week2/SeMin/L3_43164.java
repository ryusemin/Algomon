import java.util.*;

class Solution {
    static String[][] tickets;
    static boolean[] visited;
    static List<String> list = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        visited = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN");
        Collections.sort(list);
        
        return list.get(0).split(" ");
    }
    
    static void dfs(int depth, String now, String str){
        if(depth == tickets.length){
            list.add(str);
            return;
        }
        
        for(int i = 0; i < tickets.length; i++){
            if(!visited[i] && tickets[i][0].equals(now)){
                visited[i] = true;
                dfs(depth + 1, tickets[i][1], str + " " + tickets[i][1]);
                visited[i] = false;
            }
        }
    }
    
    
}