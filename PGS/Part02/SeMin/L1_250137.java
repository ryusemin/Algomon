class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int time = 1;
        int maxHealth = health;
        int myHealTime = 0;
        
        int healTime = bandage[0];
        int heal = bandage[1];
        int plusHeal = bandage[2];
        
        for( int[] attack : attacks ){
            int attackTime = attack[0];
            int damage = attack[1];
            
            while(time < attackTime){
                myHealTime++;
                if(myHealTime == healTime){
                    health += plusHeal;
                    myHealTime = 0;
                }
                if(health < maxHealth){
                    health += heal;    
                }
                if(health > maxHealth) health = maxHealth;
                time++;
                
            }    
            
            
            time++;
            health -= damage;
            myHealTime = 0;  
            if(health <= 0) return -1;  
        }
        
        
        return health;
    }
}