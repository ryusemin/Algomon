import java.util.*;

class Solution {
    public int[] solution(String s) {

        String str = s.substring(2, s.length() - 2);
        
        Map<Integer, Integer> map = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(str, ",{}");
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());
        
        int[] answer = new int[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : list) {
            answer[i++] = e.getKey();
        }
        
        return answer;
    }
}