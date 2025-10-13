import java.util.*;
class Solution {
    List<Integer> list = new ArrayList<>();
    public int solution(String[][] relation) {
        
        int n = relation.length;
        int m = relation[0].length;
        
        for (int i = 1; i < (1 << m); i++) {
            Set<String> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                String tmp = "";
                for (int k = 0; k < m; k++) {
                    if ((i & (1 << k)) != 0) {
                        tmp += (relation[j][k] + " ");
                    }
                }
                set.add(tmp);
            }
            
            if (set.size() == n && check(i)) {
                list.add(i);
            }
        }
        
        return list.size();
    }
    
    boolean check(int i) {
        for (int j : list) {
            if ((i & j) == j)
                return false;
        }
        return true;
    }
}