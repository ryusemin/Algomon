import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static char[][] map;
    static List<Point> empties, teachers;
    static boolean possible;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        empties = new ArrayList<>();
        teachers = new ArrayList<>();
        possible = false;

        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                char ch = st.nextToken().charAt(0);
                map[i][j] = ch;
                if (ch == 'X') {
                    empties.add(new Point(i, j));
                } else if (ch == 'T') {
                    teachers.add(new Point(i, j));
                }
            }
        }

        dfs(0, 0);

        System.out.println(possible ? "YES" : "NO");
    }

    static void dfs(int idx, int cnt) {
        if (possible) return;
        if (cnt == 3) {
            if (isSafe()) {
                possible = true;
            }
            return;
        }

        if (idx == empties.size()) return;

        Point p = empties.get(idx);
        map[p.r][p.c] = 'O';
        dfs(idx + 1, cnt + 1);
        map[p.r][p.c] = 'X';
        dfs(idx + 1, cnt);
    }

    static boolean isSafe() {
        for (Point t : teachers) {
            for (int d = 0; d < 4; d++) {
                int nr = t.r + dr[d];
                int nc = t.c + dc[d];

                while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (map[nr][nc] == 'O') break;
                    if (map[nr][nc] == 'S') return false;
                    nr += dr[d];
                    nc += dc[d];
                }
            }
        }

        return true;
    }
}

class Point {
    int r, c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}