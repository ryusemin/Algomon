import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int result = Integer.MAX_VALUE;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        
        
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }  
        }
        
        back(0, 0);
        System.out.println(result);
    }

    static void back(int depth, int sum){
        if(depth == 3){
            result = Math.min(result, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (canPlant(i, j)) {
                    int count = getCost(i, j);
                    mark(i, j, true);
                    back(depth + 1, sum + count);
                    mark(i, j, false);
                }
            }  
        }
    }

    static boolean canPlant(int y, int x) {
        if (visited[y][x]) return false;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) return false;
        }
        return true;
    }

    static int getCost(int y, int x) {
        int sum = map[y][x];
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            sum += map[ny][nx];
        }
        return sum;
    }

    static void mark(int y, int x, boolean flag) {
        visited[y][x] = flag;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            visited[ny][nx] = flag;
        }
    }
    
}