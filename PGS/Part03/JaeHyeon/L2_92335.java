import java.util.*;

class Solution {
    public int solution(int n, int k) {
        
        int answer = 0;
        String num = Integer.toString(n, k);
        String[] arr = num.split("0");
        
        for (String s : arr) {
            if (s.equals(""))   continue;
            
            long lnum = Long.parseLong(s);
            
            if (isPrime(lnum)) {
                answer++;
            }
        }
        
        
        return answer;
    }
    
    boolean isPrime(long num) {
        if (num < 2) return false;
        for (int i = 2; i <= (long)Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}