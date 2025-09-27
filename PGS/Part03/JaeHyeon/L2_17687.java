import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        
        StringBuilder result = new StringBuilder();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; sb.length() < t * m; i++) { 
            sb.append(Integer.toString(i, n));
        }
        
        String str = sb.toString().toUpperCase();
        for (int i = 0; result.length() < t; i++) {
            if (((i % m) + 1) == p) {
                result.append(str.charAt(i));
            }
        }
        
        return result.toString();
    }
}