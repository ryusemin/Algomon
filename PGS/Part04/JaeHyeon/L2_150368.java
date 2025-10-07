import java.util.*;
class Solution {
    List<int[]> list = new ArrayList<>();
    public int[] solution(int[][] users, int[] emoticons) {
        
        dfs(users, emoticons, new int[users.length], 0);
        
        list.sort((l1, l2) -> {
            return l1[0] == l2[0] ? l2[1] - l1[1] : l2[0] - l1[0];
        });
        
        
        return list.get(0);
    }
    
    void dfs(int[][] users, int[] emoticons, int[] sum, int cnt) {
        if (cnt == emoticons.length) {
            int plus = 0;
            int total = 0;
            for (int i = 0; i < users.length; i++) {
                if (sum[i] >= users[i][1]) plus++;
                else total += sum[i];
            }
            
            list.add(new int[]{plus, total});
            return;
        }
        
        for (int i = 10; i <= 40; i += 10) {
            int price = (emoticons[cnt] * (100 - i)) / 100;
            
            for (int j = 0; j < users.length; j++) {
                if (users[j][0] <= i) {
                    sum[j] += price;
                }
            }
            
            dfs(users, emoticons, sum, cnt + 1);      
            
            for (int j = 0; j < users.length; j++) {
                if (users[j][0] <= i) {
                    sum[j] -= price;
                }
            }
        }
    }
}