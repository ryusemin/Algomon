import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        String[] answer = {};
        
        int max = 0;
        boolean[] isPossible = new boolean[10];
        List<String> ans = new ArrayList<>();
        
        Queue<String> complete = new ArrayDeque<>();
        Queue<String> incomplete = new ArrayDeque<>();
        
        for (String exp : expressions) {
            for (int i = 0; i < exp.length(); i++) {
                char ch = exp.charAt(i);
                if (Character.isDigit(ch)) {
                    int chNum = ch - '0';
                    max = Math.max(max, chNum);
                }
            }
            
            if (exp.charAt(exp.length() - 1) == 'X') {
                incomplete.add(exp);
            } else {
                complete.add(exp);
            }
        }
        
        for (int i = max + 1; i <= 9; i++) {
            isPossible[i] = true;
        }
        
        while(!complete.isEmpty()) {
            String ex = complete.poll();
            
            for (int i = 2; i <= 9; i++) {
                if (!isPossible[i]) continue;
                StringTokenizer st = new StringTokenizer(ex, " ");
                String num1 = st.nextToken();
                String op = st.nextToken();
                String num2 = st.nextToken();
                st.nextToken();
                String result = st.nextToken();
                
                if (op.equals("+")) {
                    if ((Integer.parseInt(num1, i) + Integer.parseInt(num2, i)) 
                        != Integer.parseInt(result, i)) {
                        isPossible[i] = false;
                    }
                } else {
                    if ((Integer.parseInt(num1, i) - Integer.parseInt(num2, i)) 
                        != Integer.parseInt(result, i)) {
                        isPossible[i] = false;
                    }
                }
            }
        }
        
        while(!incomplete.isEmpty()) {
            String ex = incomplete.poll();
            
            String rs = null;
            boolean isOk = true;
            for (int i = 2; i <= 9; i++) {
                if (!isPossible[i]) continue;
                StringTokenizer st = new StringTokenizer(ex, " ");
                String num1 = st.nextToken();
                String op = st.nextToken();
                String num2 = st.nextToken();
                st.nextToken();
                String result = st.nextToken();
                
                int sum;
                if (op.equals("+")) {
                    sum = Integer.parseInt(num1, i) + Integer.parseInt(num2, i);
                } else {
                    sum = Integer.parseInt(num1, i) - Integer.parseInt(num2, i);
                }
                
                String sumStr = Integer.toString(sum, i);
                
                if (rs == null) {
                    rs = sumStr;
                } else {
                    if (!rs.equals(sumStr)) {
                        isOk = false;
                        break;
                    }
                }
            }
                        
            if (isOk) {
                ans.add(ex.substring(0, ex.length() - 1).concat(rs));
            } else {
                ans.add(ex.substring(0, ex.length() - 1).concat("?"));
            }
        }        

        return ans
            .stream()
            .toArray(String[]::new);
    }
}