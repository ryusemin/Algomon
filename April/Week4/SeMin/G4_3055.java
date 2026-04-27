import java.util.*;
import java.io.*;

class Main {
    static class Coordinate{
        char name;
        int y, x, move;
        public Coordinate(char name, int y, int x, int move){
            this.name = name;
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] arr = new char[r][c];
        boolean[][] visited = new boolean[r][c];

        Queue<Coordinate> q = new LinkedList<>();
        
        char hedgehog = 'S';
        int hy = -1;
        int hx = -1;
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char now = str.charAt(j);
                if(now == 'S') {
                    hy = i;
                    hx = j;
                    now = '.';
                }
                if(now == '*') q.offer(new Coordinate(now, i , j, -1));
                arr[i][j] = now;
            }    
        }
        q.offer(new Coordinate(hedgehog, hy, hx, 0));
        visited[hy][hx] = true;

        int answer = -1;

        while (!q.isEmpty()) {
            Coordinate m = q.poll();
            if(m.name == '*'){
                for (int i = 0; i < 4; i++) {
                    int ny = m.y + dy[i];
                    int nx = m.x + dx[i];
                    if(ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
                    if(arr[ny][nx] == 'X' || arr[ny][nx] == 'D' || arr[ny][nx] == '*') continue;
                    arr[ny][nx] = '*';
                    q.offer(new Coordinate('*', ny, nx, 0));   
                }
            }
            if(m.name == 'S'){
                for (int i = 0; i < 4; i++) {
                    int ny = m.y + dy[i];
                    int nx = m.x + dx[i];
                    if(ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
                    if(arr[ny][nx] == '*' || arr[ny][nx] == 'X') continue;
                    if(visited[ny][nx]) continue;

                    if(arr[ny][nx] == 'D') {
                        System.out.println(m.move + 1);
                        System.exit(0);
                    }
                    q.offer(new Coordinate('S', ny, nx, m.move + 1));
                    visited[ny][nx] = true;
                }
            }    
        }
        System.out.println("KAKTUS");
    }
}