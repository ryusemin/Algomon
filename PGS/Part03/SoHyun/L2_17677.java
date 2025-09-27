import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> lst1 = stringSplit(str1);
        List<String> lst2 = stringSplit(str2);

        if (lst1.isEmpty() && lst2.isEmpty()) {
            return 65536;
        }

        List<String> union = new ArrayList<>();
        List<String> inter = new ArrayList<>();

        for (String s : lst1) {
            if (lst2.remove(s)) {
                inter.add(s);
            }

            union.add(s);
        }
        union.addAll(lst2);

        double j = (double) inter.size() / (double) union.size();

        return (int) (j * 65536);
    }

    private List<String> stringSplit(String str) {
        List<String> lst = new ArrayList<>();
        str = str.toLowerCase();

        for (int i = 0; i < str.length()-1; i++) {
            String s = str.substring(i, i+2).replaceAll("[^a-z]", "");

            if (s.length() < 2)
                continue;

            lst.add(s);
        }

        return lst;
    }

}