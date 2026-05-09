import java.util.*;

class Solution {
    static int[] stones;
    public int solution(int[] stones, int k) {
        this.stones = stones;
        int answer = 0;
        
        int left = 1;
        int right = 200000000; 
        
        while(left <= right){
            int mid = (left + right) / 2;
            
            if(!check(k, mid)) right = mid - 1;
            else{ 
                left = mid + 1;
                answer = Math.max(answer, mid);
            }
        }
        return answer;
    }
    
    boolean check(int k, int friends){
        int skip = 0;
        for(int stone : stones){
            if(stone < friends) skip++; 
            else skip = 0; 
            
            if(skip == k)return false;
        }

        return true;
    }
}