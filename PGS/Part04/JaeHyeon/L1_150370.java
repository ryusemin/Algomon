import java.util.*;
class Solution {
    StringTokenizer st;
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        st = new StringTokenizer(today, ".");
        long tDate = Integer.parseInt(st.nextToken()) * 12 * 28
                    + Integer.parseInt(st.nextToken()) * 28
                    + Integer.parseInt(st.nextToken());

        for (int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String collect = st.nextToken();
            String type = st.nextToken();
            int typeNum = 0;
            
            for (int j = 0; j < terms.length; j++) {
                if (terms[j].charAt(0) == type.charAt(0)) {
                    st = new StringTokenizer(terms[j], " ");
                    st.nextToken();
                    typeNum = Integer.parseInt(st.nextToken());
                    break;
                }
            }
            
            st = new StringTokenizer(collect, ".");
            long cDate = Integer.parseInt(st.nextToken()) * 12 * 28
                        + Integer.parseInt(st.nextToken()) * 28
                        + Integer.parseInt(st.nextToken());
            
            if ((cDate + typeNum * 28) - 1 < tDate) {
                answer.add(i + 1);
            }
        }
        
        return answer.stream()
                    .mapToInt(Integer::new)
                    .toArray();
    }
}