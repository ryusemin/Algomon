import java.util.*;

class Solution {
    String[] answer;
    public String[] solution(String[][] tickets) {
        boolean[] used = new boolean[tickets.length];
        Arrays.sort(tickets, (t1, t2) -> t2[1].compareTo(t1[1]));
        
        StringBuilder sb = new StringBuilder("ICN");
        dfs(tickets, used, "ICN", sb, 0);
                
        return  answer;
    }
    
    void dfs(String[][] tickets, boolean[] used, String now, StringBuilder sb, int count) {       
        if (count == tickets.length) {
            answer = sb.toString().split(",");
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!used[i] && tickets[i][0].equals(now)) {
                int prevLen = sb.length();
                
                used[i] = true;
                sb.append(",").append(tickets[i][1]);
                dfs(tickets, used, tickets[i][1], sb, count + 1);
               
                used[i] = false;
                sb.setLength(prevLen);
            }
        }
    }
}