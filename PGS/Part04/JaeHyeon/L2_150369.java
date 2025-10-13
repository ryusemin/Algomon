class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dSum = sumA(deliveries);
        int pSum = sumA(pickups);
        
        int d = n - 1;
        int p = n - 1;
        
        while (dSum > 0 || pSum > 0) {
            
            while(d >= 0 && deliveries[d] == 0) d--;
            while(p >= 0 && pickups[p] == 0)    p--;
            
            int box = Math.min(dSum, cap);
            int empty = cap;
            
            int idx = Math.max(d, p);
            if (idx < 0)    break;
            
            int dd = d;
            while (box > 0 && dd >= 0) {
                if (deliveries[dd] == 0) {
                    dd--;
                    continue;
                }
                int take = Math.min(deliveries[dd], box);
                deliveries[dd] -= take;
                box -= take;
                dSum -= take;
                if (deliveries[dd] == 0)    dd--;
            }
            d = dd;
            
            int pp = p;
            while(empty > 0 && pp >= 0) {
                if (pickups[pp] == 0) {
                    pp--;
                    continue;
                }
                int take = Math.min(pickups[pp], empty);
                pickups[pp] -= take;
                empty -= take;
                pSum -= take;
                if (pickups[pp] == 0)   pp--;
            }
            p = pp;
 
            answer += 2 * (idx + 1);
        }
        
        return answer;
    }
    
    int sumA(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        
        return sum;
    }
} 