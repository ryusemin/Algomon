import java.util.*;
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {       
        
        int reqTime = bandage[0];
        int heal = bandage[1];
        int bonus = bandage[2];
        
        int maxTime = 0;
        Map<Integer, Integer> attack = new HashMap<>();
        for (int[] at : attacks) {
            maxTime = Math.max(maxTime, at[0]);
            attack.put(at[0], at[1]);
        }
        
        int nowHealth = health;
        int count = 0;
        
        for (int t = 1; t <= maxTime; t++) {
            if (attack.containsKey(t)) {
                count = 0;
                nowHealth -= attack.get(t);
                if (nowHealth <= 0)
                    return -1;
                continue;
            }
            
            count++;
            nowHealth += heal;
            
            if (count == reqTime) {
                nowHealth += bonus;
                count = 0;
            }
            
            if (nowHealth > health)
                nowHealth = health;
        }
        
        return nowHealth;
    }
}