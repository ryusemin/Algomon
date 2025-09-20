class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        // 시작시간 (초)
        int start = h1 * 3600 + m1 * 60 + s1;
        // 종료시간 (초)
        int end = h2 * 3600 + m2 * 60 + s2;
        
        int answer = 0;
        
        if (start % 360 == 0 || start % 360 == 12)
            answer++;
        
        while (start < end) {
            
            // 현재 시간 기준 시, 분, 초 각도
            double h = (start / (double)120) % 360;
            double m = (start / (double)10) % 360;
            double s = (start * 6) % 360;
            
            // 1초 후 각도
            double next_h = ((start + 1) / (double)120) % 360;
            double next_m = ((start + 1) / (double)10) % 360;
            double next_s = ((start + 1) * 6) % 360;
            
            if (next_h == 0) next_h = 360;
            if (next_m == 0) next_m = 360;
            if (next_s == 0) next_s = 360;
            
            // 초침, 시침이 만나는경우
            if (s < h && next_s >= next_h) answer++;
            // 초침, 분침이 만나는 경우
            if (s < m && next_s >= next_m) answer++;
            // 시침, 분침이 같아서 중복된 경우
            if (next_h == next_m) answer--;
            
            start++;
        }
        
        return answer;
    }
}