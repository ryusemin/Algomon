import java.util.*;

class Solution {
    int[] di = {1, 0, -1, 0};
    int[] dj = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        int answer = 0;
        int[][] v = new int[maps.length][maps[0].length];

        bfs(maps, v);
        answer = v[maps.length-1][maps[0].length-1];

        if (answer == 0)
            answer = -1;

        return answer;
    }

    public void bfs(int[][] maps, int[][] v) {
        int i = 0;
        int j = 0;
        v[i][j] = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {i, j});

        while (!q.isEmpty()) {
            int[] c = q.remove();
            int ci = c[0];
            int cj = c[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (ni < 0 || ni > maps.length-1 || nj < 0 || nj > maps[0].length-1)
                    continue;

                if (v[ni][nj] == 0 && maps[ni][nj] == 1) {
                    v[ni][nj] = v[ci][cj] + 1;
                    q.add(new int[] {ni, nj});
                }
            }
        }
    }
}
