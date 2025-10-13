import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Queue<String> q = new LinkedList<>();
        
        for(String city : cities){
            city = city.toLowerCase();
            boolean flag = false;
            
            for(String s : q){
                s = s.toLowerCase();
                if(s.equals(city)) {
                    answer += 1;
                    flag = true;
                    break;
                }
            }
            
            if(!flag) answer += 5;
            
            
            if(q.contains(city)){
                q.remove(city);
                q.offer(city);
                continue;
            }

            if(cacheSize == 0) continue;
            else if(q.size() == cacheSize){
                q.poll();
            }
            q.offer(city);
            
        }
        
        
        return answer;
    }
}