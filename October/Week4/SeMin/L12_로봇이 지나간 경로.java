import java.util.*;
import java.io.*;

public class Main {
    static int dir = 0;
    static String command = "";
    static int answerX = -1;
    static int answerY = -1;

    static int H, W;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static String[][] grid;

    static boolean check(int y, int x){
        return (y >= 1 && x >= 1 && y <= H && x <= W && grid[y][x].equals("#"));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] HW = br.readLine().split(" ");
        H = Integer.parseInt(HW[0]);
        W = Integer.parseInt(HW[1]);
        grid = new String[H + 1][W + 1];
        for (int i = 0; i < H; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < W; j++) {
                grid[i+1][j+1] = line[j];
            }
        }
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if(!check(i, j)) continue;
                
                int count = 0;
                int direct = -1;
                for(int d = 0; d < 4; d++){
                    int nextY = i + dy[d];
                    int nextX = j + dx[d];
                    if(check(nextY, nextX)){
                        count++;
                        direct = d; 
                    }
                }
                if (count == 1){
                    answerY = i;
                    answerX = j;
                    dir = direct;
                }


            }
        }
        System.out.println(answerY + " " + answerX);
        System.out.println(">v<^".charAt(dir));

        boolean[][] visited = new boolean[H + 1][W + 1]; 
        StringBuilder commands = new StringBuilder();

        while (true) {
            int cnt = 0, target_direction = -1;
            for (int d = 0; d < 4; d++) {
                int ny = answerY + dy[d];
                int nx = answerX + dx[d];
                if (check(ny, nx) && !visited[ny][nx]) {
                    cnt++;
                    target_direction = d;
                }
            }

            if (cnt == 0) break; 

            int diff = (target_direction - dir + 4) % 4;
            if (diff == 1) commands.append('R'); 
            else if (diff == 3) commands.append('L'); 
            dir = target_direction;

            commands.append('A'); 
            for (int rep = 0; rep < 2; rep++) {
                answerY += dy[dir];
                answerX += dx[dir];
                visited[answerY][answerX] = true; 
            }
        }

        System.out.println(commands); 
    }
}