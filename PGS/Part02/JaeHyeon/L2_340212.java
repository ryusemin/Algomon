import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int start = 1;
        int end = 100000;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            long sum = getSum(mid, diffs, times);
            
            if (sum > limit) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }    
        }
        
        return start;
    }
    
    long getSum(int n, int[] diffs, int[] times) {
        long sum = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= n) {
                sum += times[i];
            } else {
                int count = diffs[i] - n;
                sum += (count * (times[i] + times[i - 1]));
                sum += times[i];
            }
        }
        
        return sum;
    }
}