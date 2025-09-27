import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n, k);
        String[] arr = s.split("0");
        System.out.println(Arrays.toString(arr));
        
        for(int i = 0; i < arr.length; i++){
            String a = arr[i];
            if(isPrime(a)) answer++;
        }
        
        return answer;
    }
    
    public boolean isPrime(String a){
        if(a.equals("")) return false;
        long b = Long.parseLong(a);
        if(b == 0 || b == 1) return false;
        if(b == 2) return true;
        
        for(int i = 3; i <= Math.sqrt(b); i++){
            if(b % i == 0) return false;
        }
        return true;
    }
}