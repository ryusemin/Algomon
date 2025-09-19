import java.util.*;

class Solution {
    static Queue<int[]>[] recode;
    static int n;
    static int result;

    public int solution(int[][] points, int[][] routes) {
        n = routes.length;
        recode = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            recode[i] = new LinkedList<>();
        }

        recoding(points, routes);

        counting();

        return result;
    }

    public void counting() {
        int count = 0;

        while (count < n) {
            int[][] map = new int[101][101];
            count = 0;

            for (int i = 0; i < n; i++) {
                if (recode[i].isEmpty()) {
                    count++;
                    continue;
                }

                int[] tmp = recode[i].poll();
                map[tmp[0]][tmp[1]]++;
            }

            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (map[i][j] > 1)
                        result++;
                }
            }
        }
    }

    public void recoding(int[][] points, int[][] routes) {
        for (int i = 0; i < n; i++) {
            int[] route = routes[i];
            int x = points[route[0] - 1][1];
            int y = points[route[0] - 1][0];

            recode[i].add(new int[] {x, y});

            for (int j = 1; j < route.length; j++) {
                int nx = points[route[j] - 1][1];
                int ny = points[route[j] - 1][0];

                while (ny != y) {
                    if (ny > y) y++;
                    else y--;

                    recode[i].add(new int[] {x, y});
                }

                while (nx != x) {
                    if (nx > x) x++;
                    else x--;

                    recode[i].add(new int[] {x, y});
                }
            }
        }
    }
}