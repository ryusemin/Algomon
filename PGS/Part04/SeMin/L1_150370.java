import java.util.*;

class Solution {
    public List solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        int year = Integer.parseInt(today.substring(2, 4));
        int month = Integer.parseInt(today.substring(5, 7));
        int day = Integer.parseInt(today.substring(8));

        int td = (year * 12 * 28) + (month * 28) + day;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i< terms.length; i++){
            String alpha = terms[i].substring(0,1);
            int m = Integer.parseInt(terms[i].substring(2));
            
            int md = td - m * 28;
            map.put(alpha, md);
        }
        
        for(int i = 0; i < privacies.length; i++){
            int y = Integer.parseInt(privacies[i].substring(2, 4));
            int m = Integer.parseInt(privacies[i].substring(5, 7));
            int d = Integer.parseInt(privacies[i].substring(8, 10));
            String al = privacies[i].substring(11);
            
            int check = map.get(al);
            int nd = (y * 12 * 28) + (m * 28) + d;
            
            System.out.println(check);
            System.out.println(nd);
            
            
            if(check >= nd) answer.add(i+1);            
            
        }
        
        return answer;
    }
}