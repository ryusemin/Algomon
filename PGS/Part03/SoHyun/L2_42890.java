import java.util.*;

class Solution {
    List<Integer> answer = new ArrayList<>();

    public int solution(String[][] relation) {
        int n = relation.length;
        int m = relation[0].length;

        for (int i = 1; i < 1 << m; i++) {
            Set<String> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                String temp = "";
                for (int k = 0; k < m; k++) {
                    if((i & 1 << k) > 0) {
                        temp += (relation[j][k] + " ");
                    }
                }
                set.add(temp);
            }
            if (set.size() == n && check(i)) {
                answer.add(i);
            }
        }
        return answer.size();
    }

    boolean check(int i) {
        for (int j : answer) {
            if ((i & j) == j)
                return false;
        }
        return true;
    }
}