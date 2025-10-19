import java.util.*;
import java.io.*;

public class Main {
    private static final int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    private static int n, m;
    private static int[][] board = new int[15][15]; 
    private static int ans = Integer.MIN_VALUE; 


    private static boolean isOutrange(int x, int y) {
        return !(1 <= x && x <= n && 1 <= y && y <= m);
    }


    private static int conv(int x, int y) {
        return (x - 1) * m + y;
    }


    private static int[] reconv(int num) {
        int x = 1 + (num - 1) / m;
        int y = 1 + (num - 1) % m;
        return new int[]{x, y};
    }

    private static boolean[] vis = new boolean[105]; 
    private static List<Integer> lef = new ArrayList<>(); 
    private static Set<List<Integer>> se = new HashSet<>(); 
    private static List<List<Integer>> ve = new ArrayList<>(); 

    private static List<Integer> v = new ArrayList<>(); 

    private static void doit(int num) {
        if (num == 5) {
            List<Integer> newv = new ArrayList<>(v);
            Collections.sort(newv);
            if (!se.contains(newv)) {
                ve.add(newv);
                se.add(newv);
            }
            return;
        }

        for (int i = 0; i < lef.size(); i++) {
            int next_val = lef.get(i);
            if (vis[next_val]) continue;
            vis[next_val] = true;
            v.add(next_val);

            int[] cur = reconv(next_val);
            int x = cur[0];
            int y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (isOutrange(nx, ny)) continue;
                lef.add(conv(nx, ny));
            }

            doit(num + 1);

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (isOutrange(nx, ny)) continue;
                lef.remove(lef.size() - 1);
            }
            vis[next_val] = false;
            v.remove(v.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        for (int i = 1; i <= n; i++){
            String[] b = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(b[j-1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int num = conv(i, j);
                vis[num] = true;
                v.add(num);

                lef.clear();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if (isOutrange(nx, ny)) continue;
                    lef.add(conv(nx, ny));
                }

                doit(1);

                v.remove(v.size() - 1);
                vis[num] = false;
            }
        }


        for (int i = 0; i < ve.size(); i++) {
            for (int j = i + 1; j < ve.size(); j++) {
                int dup = 0; 
                for (int ii = 0; ii < 5; ii++) {
                    for (int jj = 0; jj < 5; jj++) {
                        if (ve.get(i).get(ii).equals(ve.get(j).get(jj))) {
                            dup++;
                        }
                    }
                }

                if (dup != 2) continue; 
                int val = 0; 
                for (int ii = 0; ii < 5; ii++) {
                    int num = ve.get(i).get(ii);
                    int[] cur = reconv(num);

                    int x = cur[0];
                    int y = cur[1];

                    val += board[x][y];
                }
                for (int jj = 0; jj < 5; jj++) {
                    int num = ve.get(j).get(jj);
                    int[] cur = reconv(num);

                    int x = cur[0];
                    int y = cur[1];

                    val += board[x][y];
                }

                ans = Math.max(ans, val); 
            }
        }

        System.out.println(ans);
    }
}
