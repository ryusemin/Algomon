import java.util.*;

class Solution {
    static Queue<int[]>[] memo; 
    static int size;
    static int answer;
    public int solution(int[][] points, int[][] routes) {
        size = routes.length;
        memo = new LinkedList[size];
        for(int i = 0; i < size; i++){
            memo[i] = new LinkedList<>();
        }
        function(points, routes); 
        function2(); 
        return answer;      
    }
    public static void function2(){
        int count = 0;
        while(count != size){
            int [][] map = new int [101][101];
            count = 0;
            for(int i = 0; i < size; i++){
                if(memo[i].isEmpty()){
                    count++;
                    continue;
                }
                int [] temp = memo[i].poll();
                map[temp[0]][temp[1]]++;
            }
            for(int i = 0; i < 101; i++){
                for(int j = 0; j < 101; j++){
                    if(map[i][j] > 1) answer++;
                }
            }
        }
    }

    public static void function(int [][] points, int [][] routes){
        for(int i = 0; i < size; i++){
            int [] point = points[routes[i][0] - 1];
            int x = point[0];
            int y = point[1];
            memo[i].add(new int[]{x, y});
            for(int j = 1; j < routes[0].length; j++){
                int [] next_point = points[routes[i][j] - 1];
                int nx = next_point[0];
                int ny = next_point[1];
                
                int xp = nx - x; 
                int yp = ny - y;
                while(xp != 0){
                    if(xp > 0){
                        xp--;
                        x++;
                        memo[i].add(new int[]{x, y});
                    }
                    else{
                        xp++;
                        x--;
                        memo[i].add(new int[]{x, y});
                    }
                }
                while(yp != 0){
                    if(yp > 0){
                        yp--;
                        y++;
                        memo[i].add(new int[]{x, y});
                    }
                    else{
                        yp++;
                        y--;
                        memo[i].add(new int[]{x, y});
                    }
                }
            }
        }
    }
}