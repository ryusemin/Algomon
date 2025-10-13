import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String s) {
        ArrayList<Integer> answer = new ArrayList<>();
        s = s.substring(1,s.length()-1); 
        
        String arr[] = s.split("},");
        int len = arr.length;
        
        for(int i=0;i<arr.length;i++) {
            arr[i] = arr[i].substring(1,arr[i].length());
        }
        arr[len-1] = arr[len-1].substring(0, arr[len-1].length() - 1); 
        
        Arrays.sort(arr, (s1, s2) -> s1.length() - s2.length());

        for(String a : arr) {
            String [] tmp = a.split(",");
            for(String t : tmp) {
                int num = Integer.parseInt(t);
                if(!answer.contains(num)) {
                    answer.add(num);
                }
            }
        }
        return answer;
    }
}