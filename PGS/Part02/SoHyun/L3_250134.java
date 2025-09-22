import java.util.*;

class Solution {
    public static int n;
    public static int m;
    public static int[][] map;
    public static boolean[][] red;
    public static boolean[][] blue;
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};
    public static int rex, rey, bex, bey;
    public static int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;

        n = maze.length;
        m = maze[0].length;
        map = new int[n][m];
        red = new boolean[n][m];
        blue = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 0)
                    continue;

                if (maze[i][j] == 1) {
                    red[i][j] = true;
                    rx = i;
                    ry = j;
                }
                else if (maze[i][j] == 2) {
                    blue[i][j] = true;
                    bx = i;
                    by = j;
                }
                else if (maze[i][j] != 5) {
                    if (maze[i][j] == 3) {
                        rex = i;
                        rey = j;
                    }
                    else {
                        bex = i;
                        bey = j;
                    }
                }
                else {
                    map[i][j] = maze[i][j];
                }
            }
        }
        move(rx, ry, bx, by, 0, false, false);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void move(int rx, int ry, int bx, int by, int move, boolean red_end, boolean blue_end) {
        if (!red_end && rx == rex && ry == rey)
            red_end = true;
        if (!blue_end && bx == bex && by == bey)
            blue_end = true;

        if (red_end && blue_end) {
            answer = Math.min(answer, move);
            return;
        }

        ArrayList<int[]> r_list = new ArrayList<>();
        ArrayList<int[]> b_list = new ArrayList<>();

        if (!red_end) {
            for (int i = 0; i < 4; i++) {
                int nx = dr[i] + rx;
                int ny = dc[i] + ry;
                if (validation(nx, ny) && map[nx][ny] != 5 && !red[nx][ny])
                    r_list.add(new int[] {nx, ny});
            }
        }
        else {
            r_list.add(new int[] {rx, ry});
        }

        if (!blue_end) {
            for (int i = 0; i < 4; i++) {
                int nx = dr[i] + bx;
                int ny = dc[i] + by;
                if (validation(nx, ny) && map[nx][ny] != 5 && !blue[nx][ny])
                    b_list.add(new int[] {nx, ny});
            }
        }
        else {
            b_list.add(new int[] {rx, ry});
        }

        for (int i = 0; i < r_list.size(); i++) {
            int[] r_arr = r_list.get(i);
            for (int j = 0; j < b_list.size(); j++) {
                int[] b_arr = b_list.get(j);

                if (r_arr[0] == b_arr[0] && r_arr[1] == b_arr[1])
                    continue;

                if (rx == b_arr[0] && ry == b_arr[1] && bx == r_arr[0] && by == r_arr[1])
                    continue;

                red[r_arr[0]][r_arr[1]] = true;
                blue[b_arr[0]][b_arr[1]] = true;
                move(r_arr[0], r_arr[1], b_arr[0], b_arr[1], move+1, red_end, blue_end);
                red[r_arr[0]][r_arr[1]] = false;
                blue[b_arr[0]][b_arr[1]] = false;
            }
        }
    }

    public static boolean validation(int nx, int ny) {
        if (0 <= nx && nx < n && 0 <= ny && ny < m)
            return true;
        return false;
    }
}