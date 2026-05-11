import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static String[] minerals;
    
    static int[][] arr = new int[][]{
        {1, 1, 1},
        {5, 1, 1},
        {25,5, 1}
    };
    
    public int solution(int[] picks, String[] minerals) {
        this.minerals = minerals;
        dfs(0, 0, picks);
        
        return answer;
    }
    
    static void dfs(int hp, int now, int[] picks){
        int flag = 0;
        
        for(int i = 0; i < 3; i++){
            if(picks[i] == 0) flag++; 
        }
        
        if(flag == 3 || now >= minerals.length) {
            answer = Math.min(answer, hp);
            return;
        }
        
        int max = (now + 5 <= minerals.length) ? now + 5 : minerals.length;
        
        for(int i = 0; i < 3; i ++){
            int damage = 0;
            if(picks[i] == 0) continue;
            
            for(int j = now; j < max; j++){
                int mineral = -1;
                switch(minerals[j]){
                    case("diamond"):
                        mineral = 0;
                        break;
                    case("iron"):
                        mineral = 1;
                        break;
                    case("stone"):
                        mineral = 2;
                        break;
                }
                damage += arr[i][mineral];
            }
            picks[i]--;
            dfs(hp + damage, now + 5, picks);
            picks[i]++;
        }
        
        
    }
}