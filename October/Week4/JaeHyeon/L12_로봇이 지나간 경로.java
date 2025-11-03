import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int h, w;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1}; // 상0 우1 하2 좌3
    static char[] direction = {'^', '>', 'v', '<'};
    public static void main(String[] args) throws Exception {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int startR = 0;
        int startC = 0;
        int startD = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] != '#')   continue;

                int count = 0;
                int dir = -1;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                    if (map[nr][nc] != '#')   continue;
                    count++;
                    dir = d;
                }

                if (count == 1) {
                    startR = i;
                    startC = j;
                    startD = dir;
                }
            }
        }

        sb = new StringBuilder();
        sb.append(startR + 1).append(" ").append(startC + 1).append("\n").append(direction[startD]).append("\n");

        Point start = new Point(startR, startC, startD);
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        visited[start.r][start.c] = true;

        while(!q.isEmpty()) {
            Point now = q.poll();
           
            for (int d = 0; d < 4; d++) {
                int ndir = (now.dir + d) % 4;
                int nr1 = now.r + dr[ndir];
                int nc1 = now.c + dc[ndir];

                if (nr1 < 0 || nr1 >= h || nc1 < 0 || nc1 >= w) continue;
                if (map[nr1][nc1] != '#' || visited[nr1][nc1])  continue;

                int nr2 = nr1 + dr[ndir];
                int nc2 = nc1 + dc[ndir];

                if (nr2 < 0 || nr2 >= h || nc2 < 0 || nc2 >= w) continue;
                if (map[nr2][nc2] != '#' || visited[nr2][nc2])  continue;

                int diff = (ndir - now.dir + 4) % 4;
                if (diff == 1)  sb.append('R');
                else if (diff == 3) sb.append('L');
                sb.append('A');

                q.add(new Point(nr2, nc2, ndir));
                visited[nr1][nc1] = true;
                visited[nr2][nc2] = true;
            }
        }

        System.out.println(sb.toString());
    }
}

class Point {
    int r, c, dir;
    Point (int r, int c, int dir) {
        this.r = r;
        this.c = c;
        this.dir = dir;
    }
}