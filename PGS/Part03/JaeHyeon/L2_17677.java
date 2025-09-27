import java.util.*;
class Solution {
    public int solution(String str1, String str2) {

        Map<String, Integer> map1 = getMap(str1);
        Map<String, Integer> map2 = getMap(str2);
                
        int intersection = 0;
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                intersection += Math.min(map1.get(key), map2.get(key));
            }
        }
        
        int sum1 = 0;
        int sum2 = 0;
        for (int v : map1.values())
            sum1 += v;
        for (int v : map2.values()) 
            sum2 += v;
        
        int uni = sum1 + sum2 - intersection;
        
        if (uni == 0)   return 65536;
        return (int) Math.floor((intersection * 65536) / uni);
    }
    
    Map<String, Integer> getMap(String str) {
        
        Map<String, Integer> map = new HashMap<>();
        str = str.toLowerCase();
        
        for (int i = 0; i < str.length() - 1; i++) {
            String s = str.substring(i, i + 2);
            if (s.chars().allMatch(ch -> ch >= 'a' && ch <= 'z')) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        
        return map;
    }
}