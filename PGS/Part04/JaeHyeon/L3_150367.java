import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();
        
        for (long num : numbers) {
            if (isValid(num)) answer.add(1);
            else              answer.add(0);
        }
        
        return answer.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
    }
    
    boolean isValid(long num) {
        String binary = Long.toBinaryString(num);
        int len = binary.length();

        int total = 1;
        while (total - 1 < len) total <<= 1;
        int totalLen = total - 1;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalLen - len; i++) {
            sb.append("0");
        }
        sb.append(binary);
        
        String realBinary = sb.toString();
        
        return check(realBinary);
    }
    
    boolean check(String binary) {
        int len = binary.length();
        if (len == 1)   return true;
        
        int root = len / 2;
        
        String left = binary.substring(0, root);
        String right = binary.substring(root + 1, len);
        
        if (binary.charAt(root) == '0') {
            return isZeroTree(left) && isZeroTree(right);
        }
        
        return check(left) && check(right);
    }
    
    boolean isZeroTree(String binary) {
        return binary.indexOf('1') == -1;
    }
}