import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int n = routes.length;
        
        List<int[]>[] route = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            route[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            int[] r = routes[i];

            int nowP = r[0] - 1;
            int nowR = points[nowP][0];
            int nowC = points[nowP][1];
            route[i].add(new int[]{nowR, nowC});
            
            for (int j = 1; j < r.length; j++) {
                int nextP = r[j] - 1;
                int nextR = points[nextP][0];
                int nextC = points[nextP][1];

                while (nowR < nextR) {
                    route[i].add(new int[]{++nowR, nowC});
                }
                while (nowR > nextR) {
                    route[i].add(new int[]{--nowR, nowC});
                }
                
                while (nowC < nextC) {
                    route[i].add(new int[]{nowR, ++nowC});
                }
                while (nowC > nextC) {
                    route[i].add(new int[]{nowR, --nowC});
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            count = Math.max(count, route[i].size());
        }
        
        for (int t = 0; t < count; t++) {
            int[][] map = new int[101][101];
            
            for (int i = 0; i < n; i++) {
                if (t < route[i].size()) {
                    int[] point = route[i].get(t);
                    map[point[0]][point[1]]++;
                }
            }
            
            for (int j = 0; j < 101; j++) {
                for (int k = 0; k < 101; k++) {
                    if (map[j][k] >= 2)
                        answer++;
                }
            }
        }
                
        return answer;
    }
}